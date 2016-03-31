package com.edgar.util.collection;

/**
 * 二元组.
 */
public class Pair<A, B> {

  private final A first;

  private final B second;

  private Pair(A first, B second) {
    this.first = first;
    this.second = second;
  }

  public static <A, B> Pair<A, B> create(A first, B second) {
    return new Pair<>(first, second);
  }

  public A getFirst() {
    return first;
  }

  public B getSecond() {
    return second;
  }

//  @Override
//  public boolean equals(Object o) {
//    if (o == this) { return true; }
//    if (!(o instanceof Pair)) { return false; }
//
//    Pair<?, ?> that = (Pair<?, ?>) o;
//    return new EqualsBuilder()
//            .append(this.first, that.first)
//            .append(this.second, that.second)
//            .isEquals();
//  }

  @Override
  public String toString() {
    return String.format("(%s, %s)", getFirst(), getSecond());
  }

//  @Override
//  public int hashCode() {
//    return new HashCodeBuilder()
//            .append(first)
//            .append(second)
//            .toHashCode();
//  }
}
