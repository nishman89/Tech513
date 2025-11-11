package com.sparta.pages;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;

import java.util.List;
@DefaultUrl("https://www.saucedemo.com/inventory.html")
public class InventoryPage extends PageObject{
    @FindBy(css = ".inventory_item")
    private List<WebElementFacade> inventoryItems;

    @FindBy(css = ".shopping_cart_badge")
    private WebElementFacade cartBadge;

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    private WebElementFacade addBackpackButton;

    public int getInventoryItemCount() {
        return inventoryItems.size();
    }

    public void addFirstItemToCart() {
        addBackpackButton.click();
    }

    public String getCartBadgeText() {
        return cartBadge.getText();
    }
}
