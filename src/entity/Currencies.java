/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Libor
 */
import java.math.BigDecimal;

public enum Currencies {
    EUR(new BigDecimal(1.11411)),
    USD(new BigDecimal(1)),
    RUR(new BigDecimal(0.015567)),
    GBP(new BigDecimal(1.295445));

    private final BigDecimal usdRatting;

    private Currencies(BigDecimal usdRatting) {
        this.usdRatting = usdRatting;
    }

    public BigDecimal getUsdRatting() {
        return this.usdRatting;
    }
}
