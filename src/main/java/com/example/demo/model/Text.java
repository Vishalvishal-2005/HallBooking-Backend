package com.example.demo.model;

import java.util.List;

public class Text {
    private List<String> text;

    // Constructor
    public Text(List<String> text) {
        this.text = text;
    }

    // Getter and Setter
    public List<String> getText() {
        return text;
    }

    public void setText(List<String> text) {
        this.text = text;
    }
}
