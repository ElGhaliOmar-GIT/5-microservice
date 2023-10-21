package tn.esprit.pidev.bns.entity.hadir;

import java.util.Objects;

public class CurrencyRequest {

    private final String currencyLabelInput;
    private final String currencyValueInput;
    private final String currencyLabelOutput;
    private final String currencyValueOutput;

    public CurrencyRequest(String currencyLabelInput,
                           String currencyValueInput,
                           String currencyLabelOutput,
                           String currencyValueOutput) {
        this.currencyLabelInput = currencyLabelInput;
        this.currencyValueInput = currencyValueInput;
        this.currencyLabelOutput = currencyLabelOutput;
        this.currencyValueOutput = currencyValueOutput;
    }

    public String getCurrencyLabelInput() {
        return currencyLabelInput;
    }

    public String getCurrencyValueInput() {
        return currencyValueInput;
    }

    public String getCurrencyLabelOutput() {
        return currencyLabelOutput;
    }

    public String getCurrencyValueOutput() {
        return currencyValueOutput;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrencyRequest that = (CurrencyRequest) o;
        return Objects.equals(currencyLabelInput, that.currencyLabelInput) && Objects.equals(currencyValueInput, that.currencyValueInput) && Objects.equals(currencyLabelOutput, that.currencyLabelOutput) && Objects.equals(currencyValueOutput, that.currencyValueOutput);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currencyLabelInput, currencyValueInput, currencyLabelOutput, currencyValueOutput);
    }
}
