package com.aspire.goldenapple.tools;

public class Message
{
	public String	str;
	public boolean	alive;
	public long	lastTime;
	public long	duration;

	public Message(String str, long duration)
	{
		this.str = str;
		this.duration = duration;
		lastTime = System.currentTimeMillis();
		alive = true;
	}

	public Message(String str)
	{
		this(str, 2000);
	}

	public void update()
	{
		if (alive)
		{
			if (System.currentTimeMillis() - lastTime > duration)
			{
				alive = false;
			}
		}
	}

	public boolean isAlive()
	{
		return alive;
	}

	public String getMessage()
	{
		return str;
	}
	
}
