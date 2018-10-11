package items;

import java.util.*;

public class Content {

    private List<IPhone> pageContent = new ArrayList<IPhone>();

    private IPhone firstItem;

    private IPhone secondItem;
    private int currentPageIndex = -1;


    public static Content init(List<IPhone> items) {
        Content newContent = new Content();
        newContent.pageContent = items;
        newContent.selectNextItems();
        return newContent;
    }

    public void selectNextItems() {
        try {
            firstItem = pageContent.get(++currentPageIndex);
        } catch (IndexOutOfBoundsException e) {
            firstItem = new IPhone();
        }
        try {
            secondItem = pageContent.get(++currentPageIndex);
        } catch (IndexOutOfBoundsException e) {
            currentPageIndex--;
            secondItem = new IPhone();
        }
    }

    public void selectPrevItems() {
        try {
            secondItem = pageContent.get(--currentPageIndex);
        } catch (IndexOutOfBoundsException e) {
            secondItem = new IPhone();
        }
        try {
            firstItem = pageContent.get(--currentPageIndex);
        } catch (IndexOutOfBoundsException e) {
            firstItem = new IPhone();
        }
    }

    public boolean isNextSelected() {
        return currentPageIndex + 1 < pageContent.size();
    }


    public boolean isPrevSelected() {
        return currentPageIndex - 2 >= 0;
    }


    public void setFirstItem(IPhone firstItem) {
        this.firstItem = firstItem;
    }

    public void setSecondItem(IPhone secondItem) {
        this.secondItem = secondItem;
    }

    public IPhone getFirstItem() {
        return firstItem;
    }

    public IPhone getSecondItem() {
        return secondItem;
    }

}
