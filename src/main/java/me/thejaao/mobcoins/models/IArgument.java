package me.thejaao.mobcoins.models;

import me.thejaao.mobcoins.impl.Executable;

import java.util.Arrays;

public abstract class IArgument implements Executable {

    private String name;
    private String[] alias;

    public IArgument(String name) {
        this(name, null);
    }

    public IArgument(String name, String... alias) {
        this.name = name;
        this.alias = alias;
    }

    public boolean isDouble(String valor) {
        if (valor.contains("NaN") || valor.contains("-")) {
            return false;
        }
        try {
            double d = Double.parseDouble(valor);
            if (d < 0) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public String getName() {
        return name;
    }

    public String[] getAlias() {
        return alias;
    }

    boolean equals(String name) {
        return name.equalsIgnoreCase(this.name) || (alias != null && alias.length > 0 && Arrays.asList(alias).contains(name));
    }

}
