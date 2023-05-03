public class Fighter {
    String style;

    public Fighter(String style) {
        this.style = style;
    }

    public String getStyle() {
        return style;
    }

    public Fighter match(Fighter fighter) {
        if (this.getStyle().equals("P")) {
            if (fighter.getStyle().equals("S") || fighter.getStyle().equals("L"))
                return fighter;
            else return this;
        } else if (this.getStyle().equals("S")) {
            if (fighter.getStyle().equals("R") || fighter.getStyle().equals("Y")) {
                return fighter;
            } else {
                return this;
            }
        } else if (this.getStyle().equals("R")) {
            if (fighter.getStyle().equals("P") || fighter.getStyle().equals("Y")) {
                return fighter;
            } else {
                return this;
            }}
            else if (this.getStyle().equals("L")) {
            if (fighter.getStyle().equals("R") || fighter.getStyle().equals("S")) {
                return fighter;
            } else {
                return this;
            }

        }
            else if (this.getStyle().equals("Y")) {
            if (fighter.getStyle().equals("L") || fighter.getStyle().equals("P")) {
                return fighter;
            } else {
                return this;
            }
        }
        return null;
    }


}
