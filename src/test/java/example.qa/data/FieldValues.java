package example.qa.data;

public class FieldValues {

    private String field;

    private String value;

    public FieldValues() {

    }

    public FieldValues(final String field, final String value) {

        this.field = field;
        this.value = value;
    }

    public String getField() {

        return field;
    }

    public void setField(final String field) {

        this.field = field;
    }

    public String getValue() {

        return value;
    }

    public void setValue(final String value) {

        this.value = value;
    }
}
