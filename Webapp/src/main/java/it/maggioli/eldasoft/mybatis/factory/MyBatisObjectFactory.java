package it.maggioli.eldasoft.mybatis.factory;

import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlObject;

public class MyBatisObjectFactory extends DefaultObjectFactory {

    @Override
    public <T> T create(Class<T> type) {
        return instantiate(type);
    }

    private <T> T instantiate(Class<T> clazz)  {
        if (XmlObject.class.isAssignableFrom(clazz)) {
            try {
                SchemaType schemaType = (SchemaType) Class.forName(clazz.getName()).getField("type").get(null);
                return (T) XmlBeans.getContextTypeLoader().newInstance(schemaType, null);
            } catch (ClassNotFoundException | IllegalArgumentException | SecurityException | NoSuchFieldException var4) {
                return null;
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        } else
            return super.create(clazz);
    }
}
