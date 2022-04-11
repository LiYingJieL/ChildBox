/*
package com.child.box.beautycode;

import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Locale;
import java.util.Set;

*/
/**
 * 获取spring中没有的对象
 *//*


@Service
public class BeanUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public BeanUtils() throws IllegalAccessException {
        //加载继承该类的子类，扫描成员变量
        Reflections reflections = new Reflections(this.getClass(),new FieldAnnotationsScanner());
        //把包含Resource注解的成员变量扫描出来
        Set<Field> fields = reflections.getFieldsAnnotatedWith(javax.annotation.Resource.class);

        for(Field f : fields){

            try {
                //获取成员变量类名
                String simpleName = f.getType().getSimpleName();
                //转小写，spring中管理的bean名称都是小写
                String beanName = simpleName.toLowerCase(Locale.ROOT);
                //从applicationContext中获取beanName对象
                Object bean = applicationContext.getBean(beanName);
                if(bean == null){
                    return;
                }

                f.setAccessible(true);
                f.set(this,bean);
            }catch (Exception e){
            }
        }

    }
}
*/
