package fairyShop.models.entities;

import fairyShop.models.Helper;
import fairyShop.models.Instrument;
import fairyShop.models.Present;
import fairyShop.models.Shop;

public class ShopImpl implements Shop {
    @Override
    public void craft(Present present, Helper helper) {

        while (helper.canWork() || present.isDone()){

            for (Instrument instrument : helper.getInstruments()) {

                while (!instrument.isBroken()) {

                    instrument.use();
                    present.getCrafted();
                    helper.work();
                    if(present.isDone()){
                        break;
                    }
                }
                if(present.isDone()){
                    break;
                }
            }
            if(present.isDone()){
                break;
            }
        }
        }
    }
