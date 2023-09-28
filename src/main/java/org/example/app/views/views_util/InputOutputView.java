package org.example.app.views.views_util;

public abstract class InputOutputView extends OutputView{
    public final InputReader inputReader;

    public InputOutputView() {
        this.inputReader = new InputReader();
    }

    public abstract String[] getData();
}
