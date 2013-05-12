// Generated by http://code.google.com/p/protostuff/ ... DO NOT EDIT!
// Generated from messages.proto

package com.game_machine.proto;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.List;

import com.dyuproject.protostuff.GraphIOUtil;
import com.dyuproject.protostuff.Input;
import com.dyuproject.protostuff.Message;
import com.dyuproject.protostuff.Output;
import com.dyuproject.protostuff.Pipe;
import com.dyuproject.protostuff.Schema;

public final class Entity implements com.game_machine.MessageBase, Externalizable, Message<Entity>, Schema<Entity>
{

    public static Schema<Entity> getSchema()
    {
        return DEFAULT_INSTANCE;
    }

    public static Entity getDefaultInstance()
    {
        return DEFAULT_INSTANCE;
    }

    static final Entity DEFAULT_INSTANCE = new Entity();

    
    private List<Player> player;
    private GameCommand gameCommand;
    private String clientId;

    public Entity()
    {
        
    }

    // getters and setters

    // player

    public List<Player> getPlayerList()
    {
        return player;
    }

    public void setPlayerList(List<Player> player)
    {
        this.player = player;
    }

    public Player getPlayer(int index)
    {
        return player == null ? null : player.get(index);
    }

    public int getPlayerCount()
    {
        return player == null ? 0 : player.size();
    }

    public void addPlayer(Player player)
    {
        if(this.player == null)
            this.player = new ArrayList<Player>();
        this.player.add(player);
    }

    // gameCommand

    public GameCommand getGameCommand()
    {
        return gameCommand;
    }

    public void setGameCommand(GameCommand gameCommand)
    {
        this.gameCommand = gameCommand;
    }

    // clientId

    public String getClientId()
    {
        return clientId;
    }

    public void setClientId(String clientId)
    {
        this.clientId = clientId;
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

    public Schema<Entity> cachedSchema()
    {
        return DEFAULT_INSTANCE;
    }

    // schema methods

    public Entity newMessage()
    {
        return new Entity();
    }

    public Class<Entity> typeClass()
    {
        return Entity.class;
    }

    public String messageName()
    {
        return Entity.class.getSimpleName();
    }

    public String messageFullName()
    {
        return Entity.class.getName();
    }

    public boolean isInitialized(Entity message)
    {
        return true;
    }

    public void mergeFrom(Input input, Entity message) throws IOException
    {
        for(int number = input.readFieldNumber(this);; number = input.readFieldNumber(this))
        {
            switch(number)
            {
                case 0:
                    return;
                case 1:
                    if(message.player == null)
                        message.player = new ArrayList<Player>();
                    message.player.add(input.mergeObject(null, Player.getSchema()));
                    break;

                case 2:
                    message.gameCommand = input.mergeObject(message.gameCommand, GameCommand.getSchema());
                    break;

                case 3:
                    message.clientId = input.readString();
                    break;
                default:
                    input.handleUnknownField(number, this);
            }   
        }
    }


    public void writeTo(Output output, Entity message) throws IOException
    {
        if(message.player != null)
        {
            for(Player player : message.player)
            {
                if(player != null)
                    output.writeObject(1, player, Player.getSchema(), true);
            }
        }


        if(message.gameCommand != null)
             output.writeObject(2, message.gameCommand, GameCommand.getSchema(), false);


        if(message.clientId != null)
            output.writeString(3, message.clientId, false);
    }

    public String getFieldName(int number)
    {
        switch(number)
        {
            case 1: return "player";
            case 2: return "gameCommand";
            case 3: return "clientId";
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
        __fieldMap.put("player", 1);
        __fieldMap.put("gameCommand", 2);
        __fieldMap.put("clientId", 3);
    }
    
    static final Pipe.Schema<Entity> PIPE_SCHEMA = new Pipe.Schema<Entity>(DEFAULT_INSTANCE)
    {
        protected void transfer(Pipe pipe, Input input, Output output) throws IOException
        {
            for(int number = input.readFieldNumber(wrappedSchema);; number = input.readFieldNumber(wrappedSchema))
            {
                switch(number)
                {
                    case 0:
                        return;
                    case 1:
                        output.writeObject(number, pipe, Player.getPipeSchema(), true);
                        break;

                    case 2:
                        output.writeObject(number, pipe, GameCommand.getPipeSchema(), false);
                        break;

                    case 3:
                        input.transferByteRangeTo(output, true, number, false);
                        break;

                    default:
                        input.handleUnknownField(number, wrappedSchema);
                }
            }
        }
    };

    public static Pipe.Schema<Entity> getPipeSchema()
    {
        return PIPE_SCHEMA;
    }

}