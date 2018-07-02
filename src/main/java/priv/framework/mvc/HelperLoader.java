package priv.framework.mvc;

import priv.framework.mvc.hepler.*;
import priv.framework.util.ClassUtil;

public class HelperLoader {
    public static void init(){
        Class<?>[]classList= {
                ClassHelper.class,
                BeanHelper.class,
                AopHelper.class,
                IocHelper.class,
                ControllerHelper.class,
                OrmHepler.class
        };
        for(Class<?>cls:classList){
            ClassUtil.loadClass(cls.getName(),true);
        }

    }
}
