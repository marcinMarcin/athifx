package com.github.athi.athifx.gui.menu.item;


import com.github.athi.athifx.gui.font_awesome.FontAwesome;
import com.github.athi.athifx.gui.menu.group.Group;

/**
 * Created by Athi
 */
public interface Item<GROUP extends Enum<?> & Group> {

    long id();

    String caption();

    FontAwesome icon();

    /**
     * @return - returns the menu group in witch the item will be set - can be null
     * If the group is null it means that the view wont be added to the menu.
     */
    GROUP group();

    default String itemId() {
        return String.valueOf(id() + "_" + caption()).replaceAll(" ", "");
    }
}
