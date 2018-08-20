package com.scheible.gwt.reduced.shared;

import com.scheible.gwt.reduced.shared.framework.dto.Typeable;
import java.math.BigDecimal;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 *
 * @author sj
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
@Typeable
public class MoneyAmountDto {
    
    public double value;
    public String unit;
    
    @JsOverlay
    public static MoneyAmountDto create(BigDecimal value, Currency currency) {
        MoneyAmountDto moneyAmount = new MoneyAmountDto();
        moneyAmount.value = value.doubleValue();
        moneyAmount.unit = currency.name();
        return moneyAmount;
    }
    
    @JsOverlay
    public static String getClassName() {
        return MoneyAmountDtoMeta.CLASS_NAME;
    }    
    
    @JsOverlay
    public final Currency getUnit() {
        return Currency.valueOf(unit);
    }
}
