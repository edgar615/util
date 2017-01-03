package com.edgar.util.io;

import java.io.*;

public class IOUtils {
  private IOUtils() {
    throw new AssertionError("Not instantiable: " + IOUtils.class);
  }

  /**
   * 删除文件,如果文件是目录，会递归删除目录下对文件和目录.
   *
   * @param file 要删除对文件
   * @return 如果文件被删除，返回<code>true</code>, 如果文件不存在，返回<code>false</code>
   * @throws IOException 删除过程中遇到对异常
   */
  public static boolean deleteFile(File file) throws IOException {
    if (!file.exists()) {
      return false;
    }

    File[] files = file.listFiles();

    if (files != null) {
      for (int i = 0; i < files.length; i++) {
        File childFile = files[i];
        if (childFile.equals(file)) {
          continue;
        }

        if (childFile.isDirectory()) {
          deleteFile(childFile);
        } else {
          childFile.delete();
        }
      }
    }

    return file.delete();
  }

  /**
   * 创建一个目录,该方法可以递归的创建父目录.
   *
   * @param directory 要创建的目录
   * @return <code>true</code>:成功创建目录, <code>false</code>:目录已经存在
   * @throws IOException 创建目录过程中遇到的异常
   */
  public static boolean createNewDirectory(File directory) throws IOException {
    if (directory.exists()) {
      return false;
    }

    if (!directory.mkdirs()) {
      throw new IOException("cannot create the directory: " + directory);
    }

    return true;
  }

  /**
   * 创建一个临时目录.
   *
   * @param namespace 临时目录对父目录
   * @param name      临时目录
   * @return 临时目录
   * @throws IOException 创建过程中遇到的异常
   */
  public static File createTempDirectory(String namespace, String name) throws IOException {
    File dir = File.createTempFile(namespace, "");

    if (dir.exists()) {
      deleteFile(dir);
    }

    createNewDirectory(dir);

    File tempDir = new File(dir, name);

    createNewDirectory(tempDir);

    return tempDir.getCanonicalFile();
  }

  /**
   * 将一个对象序列化为byte[].
   *
   * @param ser 要转换的对象
   * @throws IOException 序列化过程中遇到对异常
   */
  public static <T extends Serializable> byte[] serialize(T ser)
          throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    ObjectOutputStream oos = new ObjectOutputStream(baos);
    try {
      oos.writeObject(ser);
    } finally {
      oos.close();
    }
    return baos.toByteArray();
  }

  /**
   * 将byte[]反序列化为一个对象.
   *
   * @param array 需要反序列化对byte数组
   * @throws IOException            反序列化过程中遇到的异常
   * @throws ClassNotFoundException 需要反序列化的类不存在
   */
  @SuppressWarnings("unchecked")
  public static <T extends Serializable> T deserialize(byte[] array)
          throws IOException, ClassNotFoundException {
    ByteArrayInputStream bais = new ByteArrayInputStream(array);
    ObjectInputStream ois = new ObjectInputStream(bais);

    try {
      return (T) ois.readObject();
    } finally {
      ois.close();
    }
  }

  /**
   * 将inputStream中所有内容复制到OutputStream
   *
   * @param in  the input stream
   * @param out the output stream
   */
  public static void copy(InputStream in, OutputStream out) throws IOException {
    copy(in, out, -1);
  }

  /**
   * 将inputStream中numBytes个字节复制到OutputStream
   *
   * @param in       the input stream
   * @param out      the output stream
   * @param numBytes 需要复制对字节数，如果<code>numBytes < 0</code>，则复制全部字节
   */
  public static void copy(InputStream in, OutputStream out, int numBytes)
          throws IOException {
    if (numBytes == 0) { return; }

    int n;

    if (numBytes < 0) {
      byte[] b = new byte[2048];
      while ((n = in.read(b, 0, b.length)) > 0) {
        out.write(b, 0, n);
      }
    } else {
      int offset = 0;
      byte[] b = new byte[numBytes];
      while (numBytes > 0 && (n = in.read(b, offset, numBytes)) > 0) {
        offset += n;
        numBytes -= n;
      }
      out.write(b);
    }
  }

  /**
   * 将Reader中所有内容复制到Writer
   *
   * @param in  the reader
   * @param out the writer
   */
  public static void copy(Reader in, Writer out)
          throws IOException {
    copy(in, out, -1);
  }

  /**
   * 将Reader中numBytes个字节所有内容复制到Writer
   *
   * @param in       the reader
   * @param out      the writer
   * @param numBytes 需要复制对字节数，如果<code>numBytes < 0</code>，则复制全部字节
   */
  public static void copy(Reader in, Writer out, int numBytes)
          throws IOException {
    if (numBytes == 0) { return; }

    int n;

    if (numBytes < 0) {
      char[] b = new char[2048];
      while ((n = in.read(b, 0, b.length)) > 0) { out.write(b, 0, n); }
    } else {
      int offset = 0;
      char[] b = new char[numBytes];
      while (numBytes > 0 && (n = in.read(b, offset, numBytes)) > 0) {
        offset += n;
        numBytes -= n;
      }
      out.write(b);
    }
  }

  /**
   * 递归删除文件.
   *
   * @param path 需要删除的文件
   * @return 如果文件被删除，返回true
   */
  public static boolean forceDeletePath(File path) {
    if (path == null) {
      return false;
    }
    if (path.exists() && path.isDirectory()) {
      File[] files = path.listFiles();
      for (File file : files) {
        if (file.isDirectory()) {
          forceDeletePath(file);
        } else {
          file.delete();
        }
      }
    }
    return path.delete();
  }
}