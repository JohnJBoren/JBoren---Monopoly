public class Money {
    Double money;

    public Money() {
        this.money = 0.0;
    }

    public Money(Double money) {
        this.money = money;
    }

    public Double getMoney() {
        return this.money;
    }

    public void addMoney(Double amount) {
        this.money += amount;
    }

    public void substractMoney(Double amount) {
        this.money -= amount;
    }

    public boolean isBroke() {
        return this.money < 0.0;
    }
}