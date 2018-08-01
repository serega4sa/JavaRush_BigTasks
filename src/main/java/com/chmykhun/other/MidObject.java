package com.chmykhun.other;

import java.util.ArrayList;
import java.util.List;

/**
 * Get middle object from simply connected array by one iteration
 * */
public class MidObject {
    private CustomElement firstElement;
    private int arrayLength;

    public MidObject() {
        arrayLength = (int) (Math.random() * 10) + 2;
        List<CustomElement> initialArray = new ArrayList<CustomElement>();
        for (int i = 0; i < arrayLength ; i++) {
            CustomElement e = new CustomElement(i);
            initialArray.add(e);
            if (i > 1) {
                initialArray.get(i - 1).setNext(e);
            } else {
                firstElement = e;
            }
        }
    }

    public int getArrayLength() {
        return arrayLength;
    }

    public class CustomElement {
        private CustomElement next;
        private int elementId;

        CustomElement(int elementId) {
            this.elementId = elementId;
        }

        public CustomElement getNext() {
            return next;
        }

        public void setNext(CustomElement next) {
            this.next = next;
        }

        public int getElementId() {
            return elementId;
        }
    }

    public CustomElement getMidElement() {
        return getMidElement(firstElement);
    }

    public CustomElement getMidElement(CustomElement head) {
        CustomElement fast = head;
        CustomElement slow = head;

        while (fast != null && fast.getNext() != null) {
            fast = fast.getNext().getNext();
            slow = slow.getNext();
        }

        return slow;
    }
}
