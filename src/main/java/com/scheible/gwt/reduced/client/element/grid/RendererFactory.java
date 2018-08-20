package com.scheible.gwt.reduced.client.element.grid;

import com.scheible.gwt.reduced.client.framework.browser.Date;
import com.scheible.gwt.reduced.shared.Iso8601DateTimeDto;
import com.scheible.gwt.reduced.shared.MoneyAmountDto;

/**
 *
 * @author sj
 */
public class RendererFactory {

    public static CellRenderer<?> createRenderer(String className) {
        if (Integer.class.getName().equals(className)) {
            return createIntegerRenderer();
        } else if (String.class.getName().equals(className)) {
            return createStringRenderer();
        } else if (Iso8601DateTimeDto.getClassName().equals(className)) {
            return createIso8601DateTimeRenderer();
        } else if (MoneyAmountDto.getClassName().equals(className)) {
            return createMoneyAmountRenderer();
        } else {
            throw new IllegalStateException("Unknown column type '" + className + "'!");
        }
    }

    private static CellRenderer<Double> createIntegerRenderer() {
        return (Double value) -> value.toString();
    }

    private static CellRenderer<String> createStringRenderer() {
        return (String value) -> value;
    }

    private static CellRenderer<Iso8601DateTimeDto> createIso8601DateTimeRenderer() {
        return (Iso8601DateTimeDto value) -> new Date(value.value).toLocaleString();
    }

    private static CellRenderer<MoneyAmountDto> createMoneyAmountRenderer() {
        return (MoneyAmountDto value) -> value.value + " " + value.unit;
    }
}
