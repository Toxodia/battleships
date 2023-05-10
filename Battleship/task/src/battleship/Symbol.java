package battleship;

public enum Symbol {
    SHIP_SYMBOL("O"),
    SEA_SYMBOL("~"),
    HIT_SYMBOL("X"),
    MISS_SYMBOL("M");
    private final String symbol;
    Symbol(String symbol) {
        this.symbol = symbol;
    }
    public String getSymbol() {
        return symbol;
    }
}
