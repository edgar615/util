package com.github.edgar615.util.search;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by Edgar on 2017/5/16.
 *
 * @author Edgar  Date 2017/5/16
 */
class GoeCreator implements CriterionCreator {


    private static final String LT = "<";
    private static final String LE = "<=";

    @Override
    public List<Criterion> create(String field, String opValue, boolean negation) {
        if (opValue.startsWith(LT)
                && !opValue.startsWith(LE)) {
            String value = opValue.substring(1).trim();
            Preconditions.checkArgument(opValue.length() > 1,
                    "Problems parsing queryString: %s",
                    negation ? "-" + field : field + ":" + opValue);
            if (negation) {
                return Lists.newArrayList(new Criterion(field, Op.GE, value));
            }
            return Lists.newArrayList(new Criterion(field, Op.LT, value));
        }
        if (opValue.startsWith(LE)) {
            String value = opValue.substring(2).trim();
            Preconditions.checkArgument(opValue.length() > 2,
                    "Problems parsing queryString: %s",
                    negation ? "-" + field : field + ":" + opValue);
            if (negation) {
                return Lists.newArrayList(new Criterion(field, Op.GT, value));
            }
            return Lists.newArrayList(new Criterion(field, Op.LE, value));
        }
        return null;
    }
}
