package org.litespring.test.v2;

import com.sun.corba.se.impl.io.TypeMismatchException;
import org.junit.Assert;
import org.junit.Test;
import org.litespring.beans.SimpleTypeConverter;
import org.litespring.beans.TypeConverter;

public class TypeConverterText {
    @Test
    public void testConvertStringToInt(){
        TypeConverter converter =  new SimpleTypeConverter();
        Integer integer =  converter.convertIfNecessary("3",Integer.class);
        Assert.assertEquals(3, integer.intValue());

        try {
            converter.convertIfNecessary("3.1", Integer.class);
        }catch (Exception e){
            return;
        }
        Assert.fail();

    }


    @Test
    public void testConvertStringToBoolean(){
        TypeConverter converter =  new SimpleTypeConverter();
        Boolean b =  converter.convertIfNecessary("true",Boolean.class);
        Assert.assertEquals(b.booleanValue(), true);

        try {
            converter.convertIfNecessary("xxxyyy", Boolean.class);
        }catch (Exception e){
            return;
        }
        Assert.fail();

    }

}
