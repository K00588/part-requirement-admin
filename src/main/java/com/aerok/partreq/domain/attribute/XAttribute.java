package com.aerok.partreq.domain.attribute;

public class XAttribute {
    private String field;
    private Object value;
    private int length;
    private boolean required;

    public XAttribute() {}

    public XAttribute(String field, Object value, int length, boolean required) {
        this.field = field;
        this.value = value;
        this.length = length;
        this.required = required;
    }

    // Getter/Setter
    public String getField() { return field; }
    public void setField(String field) { this.field = field; }

    public Object getValue() { return value; }
    public void setValue(Object value) { this.value = value; }

    public int getLength() { return length; }
    public void setLength(int length) { this.length = length; }

    public boolean isRequired() { return required; }
    public void setRequired(boolean required) { this.required = required; }

    public String format() {
        String valStr = (value != null) ? value.toString() : "";
        return String.format("%-" + length + "s", valStr);
    }
}
