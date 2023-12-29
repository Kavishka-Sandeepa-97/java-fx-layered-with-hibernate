package boService;

import boService.Custom.impl.CustomerBoImpl;
import boService.Custom.impl.ItemBoImpl;
import boService.Custom.impl.OrderBoImpl;
import dao.utill.BoType;

public class BoFactory {

    static BoFactory boFactory;
    private BoFactory(){

    }

    public static BoFactory getInstance(){
        return boFactory==null?(boFactory=new BoFactory()):boFactory;
    }
    public <T extends SuperBo>T getBo(BoType type){
        switch (type){
            case CUSTOMER:return (T) new CustomerBoImpl();
            case ITEM:return (T) new ItemBoImpl();
            case ORDER:return (T) new OrderBoImpl();
        }
        return null;
    }
}
