package com.example.bugreport.records;

/// Used to showcase different bug severities
public enum Severity{
    LOW(0x2ECC71), MEDIUM(0xF1C40F), HIGH(0xE67E22), CRITICAL(0xE74C3C);
    private final int color;
    Severity(int color) { this.color = color; }
    public int getColor() { return color; }
}
