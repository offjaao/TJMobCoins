package me.thejaao.mobcoins.model;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public abstract class Manager<T> {

    private List<T> elements = Lists.newArrayList();

    public List<T> getElements() {
        return elements;
    }

    public Optional<T> get(Predicate<? super T> predicate) {
        return elements.stream().filter(predicate).findFirst();
    }
}