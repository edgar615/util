package com.github.edgar615.util.search;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * Created by Edgar on 2017/10/23.
 *
 * @author Edgar  Date 2017/10/23
 */
public class MapPredicate implements BiFunction<Map<String, Object>, Criterion, Boolean> {

  @Override
  public Boolean apply(Map<String, Object> map, Criterion criterion) {
    if (criterion.op() == Op.IS_NULL) {
      return !map.containsKey(criterion.field())
          || map.get(criterion.field()) == null;
    }
    if (criterion.op() == Op.IS_NOT_NULL) {
      return map.containsKey(criterion.field())
          && map.get(criterion.field()) != null;
    }
    if (!map.containsKey(criterion.field())) {
      return false;
    }
    if (criterion.op() == Op.EQ) {
      return criterion.value().toString().equals(map.get(criterion.field()).toString());
    }
    if (criterion.op() == Op.NE) {
      return !criterion.value().toString().equals(map.get(criterion.field()).toString());
    }
    Boolean gt = checkGreat(map, criterion);
    if (gt != null) {
      return gt;
    }
    Boolean ge = checkGreatEq(map, criterion);
    if (ge != null) {
      return ge;
    }
    Boolean lt = checkLess(map, criterion);
    if (lt != null) {
      return lt;
    }
    Boolean le = checkLessEq(map, criterion);
    if (le != null) {
      return le;
    }
    Boolean between = checkBetween(map, criterion);
    if (between != null) {
      return between;
    }
    Boolean sw = checkSw(map, criterion);
    if (sw != null) {
      return sw;
    }
    Boolean ew = checkEw(map, criterion);
    if (ew != null) {
      return ew;
    }
    Boolean cn = checkCn(map, criterion);
    if (cn != null) {
      return cn;
    }
    Boolean in = checkIn(map, criterion);
    if (in != null) {
      return in;
    }
    Boolean ni = checkNotIn(map, criterion);
    if (ni != null) {
      return ni;
    }
    return false;
  }

  private Boolean checkNotIn(Map<String, Object> map, Criterion criterion) {
    if (criterion.op() == Op.NOT_IN) {
      if (criterion.value() instanceof List) {
        List list = (List) criterion.value();
        for (Object o : list) {
          if (o.toString().equals(map.get(criterion.field()).toString())) {
            return false;
          }
        }
        return true;
      } else {
        return false;
      }
    }
    return null;
  }

  private Boolean checkIn(Map<String, Object> map, Criterion criterion) {
    if (criterion.op() == Op.IN) {
      if (criterion.value() instanceof List) {
        List list = (List) criterion.value();
        for (Object o : list) {
          if (o.toString().equals(map.get(criterion.field()).toString())) {
            return true;
          }
        }
        return false;
      } else {
        return false;
      }
    }
    return null;
  }

  private Boolean checkCn(Map<String, Object> map, Criterion criterion) {
    if (criterion.op() == Op.CN) {
      String c1 = map.get(criterion.field()).toString();
      String c2 = criterion.value().toString();
      return c1.contains(c2);
    }
    return null;
  }

  private Boolean checkEw(Map<String, Object> map, Criterion criterion) {
    if (criterion.op() == Op.EW) {
      String c1 = map.get(criterion.field()).toString();
      String c2 = criterion.value().toString();
      return c1.endsWith(c2);
    }
    return null;
  }

  private Boolean checkSw(Map<String, Object> map, Criterion criterion) {
    if (criterion.op() == Op.SW) {
      String c1 = map.get(criterion.field()).toString();
      String c2 = criterion.value().toString();
      return c1.startsWith(c2);
    }
    return null;
  }

  private Boolean checkBetween(Map<String, Object> map, Criterion criterion) {
    if (criterion.op() == Op.BETWEEN) {
      String c1 = map.get(criterion.field()).toString();
      if (criterion.value() == null
          && criterion.secondValue() == null) {
        return false;
      }
      if (criterion.value() != null
          && criterion.secondValue() == null) {
        String c2 = criterion.value().toString();
        return c1.compareTo(c2) >= 0;
      }
      if (criterion.value() == null
          && criterion.secondValue() != null) {
        String c3 = criterion.secondValue().toString();
        return c1.compareTo(c3) <= 0;
      }
      if (criterion.value() != null
          && criterion.secondValue() != null) {
        String c2 = criterion.value().toString();
        String c3 = criterion.secondValue().toString();
        return c1.compareTo(c2) >= 0 && c1.compareTo(c3) <= 0;
      }
    }
    return null;
  }

  private Boolean checkGreat(Map<String, Object> map, Criterion criterion) {
    if (criterion.op() == Op.GT) {
      //比较字符串，string不能直接和number比较，Comparable必须是同类型
      String c1 = map.get(criterion.field()).toString();
      String c2 = criterion.value().toString();
      return c1.compareTo(c2) > 0;
    }
    return null;
  }

  private Boolean checkGreatEq(Map<String, Object> map, Criterion criterion) {
    if (criterion.op() == Op.GE) {
      String c1 = map.get(criterion.field()).toString();
      String c2 = criterion.value().toString();
      return c1.compareTo(c2) >= 0;
    }
    return null;
  }

  private Boolean checkLess(Map<String, Object> map, Criterion criterion) {
    if (criterion.op() == Op.LT) {
      String c1 = map.get(criterion.field()).toString();
      String c2 = criterion.value().toString();
      return c1.compareTo(c2) < 0;
    }
    return null;
  }

  private Boolean checkLessEq(Map<String, Object> map, Criterion criterion) {
    if (criterion.op() == Op.LE) {
      String c1 = map.get(criterion.field()).toString();
      String c2 = criterion.value().toString();
      return c1.compareTo(c2) <= 0;
    }
    return null;
  }
}
