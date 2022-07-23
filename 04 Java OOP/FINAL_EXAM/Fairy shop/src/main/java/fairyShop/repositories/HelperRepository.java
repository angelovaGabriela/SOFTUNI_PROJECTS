package fairyShop.repositories;

import fairyShop.models.Helper;

import java.util.ArrayList;
import java.util.Collection;

public class HelperRepository implements Repository<Helper>{

    private Collection<Helper> helpers;

    public HelperRepository() {
        this.helpers = new ArrayList<>();
    }

    @Override
    public Collection<Helper> getModels() {
        return null;
    }


    @Override
    public void add(Helper model) {

    }

    @Override
    public boolean remove(Helper model) {
        return false;
    }

    @Override
    public Helper findByName(String name) {
        return null;
    }
}
