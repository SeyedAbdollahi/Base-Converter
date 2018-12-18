package seyedabdollahi.ir.EventBus;

import java.util.List;

import seyedabdollahi.ir.Models.Base;

public class ChangeNumber {

    private List<Base> bases;
    private int position;

    public ChangeNumber(List<Base> bases, int position) {
        this.bases = bases;
        this.position = position;
    }

    public List<Base> getBases() {
        return bases;
    }

    public void setBases(List<Base> bases) {
        this.bases = bases;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
