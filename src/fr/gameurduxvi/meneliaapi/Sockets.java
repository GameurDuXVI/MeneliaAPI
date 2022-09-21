package fr.gameurduxvi.meneliaapi;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Sockets {
	public int getOtherServerOnlinePlayers(String ip, int port) {
		try {
			synchronized (this) {
				Socket socket = new Socket();
				socket.connect(new InetSocketAddress(ip, port), 1000);
				
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
				DataInputStream in = new DataInputStream(socket.getInputStream());
				
				out.write(0xFE);
				
				StringBuilder str = new StringBuilder();
				
				int b;
				while((b = in.read()) != -1) {
					if(b != 0 && b > 16 && b != 255 && b != 23 && b != 24) {
						str.append((char) b);
					}
				}
				
				String[] data = str.toString().split("§");
				int onlinePlayers = Integer.valueOf(data[1]);
				
				socket.close();
				return onlinePlayers;
			}		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public int getOtherServerMaxPlayers(String ip, int port) {
		try {
			synchronized (this) {
				Socket socket = new Socket();
				socket.connect(new InetSocketAddress(ip, port), 1000);
				
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
				DataInputStream in = new DataInputStream(socket.getInputStream());
				
				out.write(0xFE);
				
				StringBuilder str = new StringBuilder();
				
				int b;
				while((b = in.read()) != -1) {
					if(b != 0 && b > 16 && b != 255 && b != 23 && b != 24) {
						str.append((char) b);
					}
				}
				
				String[] data = str.toString().split("§");				
				int maxPlayers = Integer.valueOf(data[2]);
				
				socket.close();
				return maxPlayers;
			}		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public String getOtherServerMotd(String ip, int port) {
		try {
			synchronized (this) {
				Socket socket = new Socket();
				socket.connect(new InetSocketAddress(ip, port), 1000);
				
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
				DataInputStream in = new DataInputStream(socket.getInputStream());
				
				out.write(0xFE);
				
				StringBuilder str = new StringBuilder();
				
				int b;
				while((b = in.read()) != -1) {
					if(b != 0 && b > 16 && b != 255 && b != 23 && b != 24) {
						str.append((char) b);
					}
				}
				
				String[] data = str.toString().split("§");
				String motd = data[0];

				socket.close();
				return motd;
			}		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "";
	}
}
