
package com.game_machine.entity_system.generated;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.List;

import com.dyuproject.protostuff.ByteString;
import com.dyuproject.protostuff.GraphIOUtil;
import com.dyuproject.protostuff.Input;
import com.dyuproject.protostuff.Message;
import com.dyuproject.protostuff.Output;

import java.io.ByteArrayOutputStream;
import com.dyuproject.protostuff.JsonIOUtil;
import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtobufIOUtil;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.game_machine.entity_system.generated.Entity;

import com.dyuproject.protostuff.Pipe;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.UninitializedMessageException;

public final class Vector3  implements Externalizable, Message<Vector3>, Schema<Vector3>
{




    public static Schema<Vector3> getSchema()
    {
        return DEFAULT_INSTANCE;
    }

    public static Vector3 getDefaultInstance()
    {
        return DEFAULT_INSTANCE;
    }

    static final Vector3 DEFAULT_INSTANCE = new Vector3();



    public Float x;



    public Float y;



    public Float z;



    public Integer xi;



    public Integer yi;



    public Integer zi;


    


    public Vector3()
    {
        
    }






    

	public Float getX() {
		return x;
	}
	
	public Vector3 setX(Float x) {
		this.x = x;
		return this;
	}
	
	public Boolean hasX()  {
        return x == null ? false : true;
    }



    

	public Float getY() {
		return y;
	}
	
	public Vector3 setY(Float y) {
		this.y = y;
		return this;
	}
	
	public Boolean hasY()  {
        return y == null ? false : true;
    }



    

	public Float getZ() {
		return z;
	}
	
	public Vector3 setZ(Float z) {
		this.z = z;
		return this;
	}
	
	public Boolean hasZ()  {
        return z == null ? false : true;
    }



    

	public Integer getXi() {
		return xi;
	}
	
	public Vector3 setXi(Integer xi) {
		this.xi = xi;
		return this;
	}
	
	public Boolean hasXi()  {
        return xi == null ? false : true;
    }



    

	public Integer getYi() {
		return yi;
	}
	
	public Vector3 setYi(Integer yi) {
		this.yi = yi;
		return this;
	}
	
	public Boolean hasYi()  {
        return yi == null ? false : true;
    }



    

	public Integer getZi() {
		return zi;
	}
	
	public Vector3 setZi(Integer zi) {
		this.zi = zi;
		return this;
	}
	
	public Boolean hasZi()  {
        return zi == null ? false : true;
    }



  
    // java serialization

    public void readExternal(ObjectInput in) throws IOException
    {
        GraphIOUtil.mergeDelimitedFrom(in, this, this);
    }

    public void writeExternal(ObjectOutput out) throws IOException
    {
        GraphIOUtil.writeDelimitedTo(out, this, this);
    }

    // message method

    public Schema<Vector3> cachedSchema()
    {
        return DEFAULT_INSTANCE;
    }

    // schema methods

    public Vector3 newMessage()
    {
        return new Vector3();
    }

    public Class<Vector3> typeClass()
    {
        return Vector3.class;
    }

    public String messageName()
    {
        return Vector3.class.getSimpleName();
    }

    public String messageFullName()
    {
        return Vector3.class.getName();
    }

    public boolean isInitialized(Vector3 message)
    {
        return true;
    }

    public void mergeFrom(Input input, Vector3 message) throws IOException
    {
        for(int number = input.readFieldNumber(this);; number = input.readFieldNumber(this))
        {
            switch(number)
            {
                case 0:
                    return;

            	case 1:


                	message.x = input.readFloat();
                	break;

                	


            	case 2:


                	message.y = input.readFloat();
                	break;

                	


            	case 3:


                	message.z = input.readFloat();
                	break;

                	


            	case 4:


                	message.xi = input.readInt32();
                	break;

                	


            	case 5:


                	message.yi = input.readInt32();
                	break;

                	


            	case 6:


                	message.zi = input.readInt32();
                	break;

                	


            
                default:
                    input.handleUnknownField(number, this);
            }   
        }
    }


    public void writeTo(Output output, Vector3 message) throws IOException
    {

    	

    	


    	if(message.x != null)
            output.writeFloat(1, message.x, false);

    	


    	

    	


    	if(message.y != null)
            output.writeFloat(2, message.y, false);

    	


    	

    	


    	if(message.z != null)
            output.writeFloat(3, message.z, false);

    	


    	

    	


    	if(message.xi != null)
            output.writeInt32(4, message.xi, false);

    	


    	

    	


    	if(message.yi != null)
            output.writeInt32(5, message.yi, false);

    	


    	

    	


    	if(message.zi != null)
            output.writeInt32(6, message.zi, false);

    	


    	
    }

    public String getFieldName(int number)
    {
        switch(number)
        {

        	case 1: return "x";

        	case 2: return "y";

        	case 3: return "z";

        	case 4: return "xi";

        	case 5: return "yi";

        	case 6: return "zi";

            default: return null;
        }
    }

    public int getFieldNumber(String name)
    {
        final Integer number = __fieldMap.get(name);
        return number == null ? 0 : number.intValue();
    }

    private static final java.util.HashMap<String,Integer> __fieldMap = new java.util.HashMap<String,Integer>();
    static
    {

    	__fieldMap.put("x", 1);

    	__fieldMap.put("y", 2);

    	__fieldMap.put("z", 3);

    	__fieldMap.put("xi", 4);

    	__fieldMap.put("yi", 5);

    	__fieldMap.put("zi", 6);

    }
   
   public static List<String> getFields() {
	ArrayList<String> fieldNames = new ArrayList<String>();
	String fieldName = null;
	Integer i = 1;
	
    while(true) { 
		fieldName = Vector3.getSchema().getFieldName(i);
		if (fieldName == null) {
			break;
		}
		fieldNames.add(fieldName);
		i++;
	}
	return fieldNames;
}

public static Vector3 parseFrom(byte[] bytes) {
	Vector3 message = new Vector3();
	ProtobufIOUtil.mergeFrom(bytes, message, RuntimeSchema.getSchema(Vector3.class));
	return message;
}

public Vector3 clone() {
	byte[] bytes = this.toByteArray();
	Vector3 vector3 = Vector3.parseFrom(bytes);
	return vector3;
}
	
public byte[] toByteArray() {
	return toProtobuf();
	//return toJson();
}

public byte[] toJson() {
	boolean numeric = false;
	ByteArrayOutputStream out = new ByteArrayOutputStream();
	try {
		JsonIOUtil.writeTo(out, this, Vector3.getSchema(), numeric);
	} catch (IOException e) {
		e.printStackTrace();
		throw new RuntimeException("Json encoding failed");
	}
	return out.toByteArray();
}
		
public byte[] toProtobuf() {
	LinkedBuffer buffer = LinkedBuffer.allocate(8024);
	byte[] bytes = null;

	try {
		bytes = ProtobufIOUtil.toByteArray(this, RuntimeSchema.getSchema(Vector3.class), buffer);
		buffer.clear();
	} catch (Exception e) {
		e.printStackTrace();
		throw new RuntimeException("Protobuf encoding failed");
	}
	return bytes;
}

}
