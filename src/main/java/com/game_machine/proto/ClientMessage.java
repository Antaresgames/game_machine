// Generated by http://code.google.com/p/protostuff/ ... DO NOT EDIT!
// Generated from messages.proto

package com.game_machine.proto;

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
import com.dyuproject.protostuff.Schema;

public final class ClientMessage implements Externalizable, Message<ClientMessage>, Schema<ClientMessage>
{

    public static Schema<ClientMessage> getSchema()
    {
        return DEFAULT_INSTANCE;
    }

    public static ClientMessage getDefaultInstance()
    {
        return DEFAULT_INSTANCE;
    }

    static final ClientMessage DEFAULT_INSTANCE = new ClientMessage();

    
    private ByteString data;
    private List<Player> players;
    private List<GameCommand> gameCommands;

    public ClientMessage()
    {
        
    }

    // getters and setters

    // data

    public ByteString getData()
    {
        return data;
    }

    public void setData(ByteString data)
    {
        this.data = data;
    }

    // players

    public List<Player> getPlayersList()
    {
        return players;
    }

    public void setPlayersList(List<Player> players)
    {
        this.players = players;
    }

    // gameCommands

    public List<GameCommand> getGameCommandsList()
    {
        return gameCommands;
    }

    public void setGameCommandsList(List<GameCommand> gameCommands)
    {
        this.gameCommands = gameCommands;
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

    public Schema<ClientMessage> cachedSchema()
    {
        return DEFAULT_INSTANCE;
    }

    // schema methods

    public ClientMessage newMessage()
    {
        return new ClientMessage();
    }

    public Class<ClientMessage> typeClass()
    {
        return ClientMessage.class;
    }

    public String messageName()
    {
        return ClientMessage.class.getSimpleName();
    }

    public String messageFullName()
    {
        return ClientMessage.class.getName();
    }

    public boolean isInitialized(ClientMessage message)
    {
        return true;
    }

    public void mergeFrom(Input input, ClientMessage message) throws IOException
    {
        for(int number = input.readFieldNumber(this);; number = input.readFieldNumber(this))
        {
            switch(number)
            {
                case 0:
                    return;
                case 1:
                    message.data = input.readBytes();
                    break;
                case 2:
                    if(message.players == null)
                        message.players = new ArrayList<Player>();
                    message.players.add(input.mergeObject(null, Player.getSchema()));
                    break;

                case 3:
                    if(message.gameCommands == null)
                        message.gameCommands = new ArrayList<GameCommand>();
                    message.gameCommands.add(input.mergeObject(null, GameCommand.getSchema()));
                    break;

                default:
                    input.handleUnknownField(number, this);
            }   
        }
    }


    public void writeTo(Output output, ClientMessage message) throws IOException
    {
        if(message.data != null)
            output.writeBytes(1, message.data, false);

        if(message.players != null)
        {
            for(Player players : message.players)
            {
                if(players != null)
                    output.writeObject(2, players, Player.getSchema(), true);
            }
        }


        if(message.gameCommands != null)
        {
            for(GameCommand gameCommands : message.gameCommands)
            {
                if(gameCommands != null)
                    output.writeObject(3, gameCommands, GameCommand.getSchema(), true);
            }
        }

    }

    public String getFieldName(int number)
    {
        return Integer.toString(number);
    }

    public int getFieldNumber(String name)
    {
        return Integer.parseInt(name);
    }
    
}