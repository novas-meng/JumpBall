package com.example.jumpball;
 

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.DigestException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.format.Time;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
public class bluetooth extends Activity {
	static enum ServerOrCilent{
		NONE,
		SERVICE,
		CILENT
	};
	
	static String receive=new String();
	static String state=null;
	static String blockreceice;
	ArrayList<String> deviceArray=new ArrayList<String>();
	Set<BluetoothDevice> set;
	 Typeface typeFace;
	 TextView t1,t2;
	static float w,h;
	public static final String PROTOCOL_SCHEME_L2CAP = "btl2cap";
	public static final String PROTOCOL_SCHEME_RFCOMM = "btspp";
	public static final String PROTOCOL_SCHEME_BT_OBEX = "btgoep";
	public static final String PROTOCOL_SCHEME_TCP_OBEX = "tcpobex";
	static int i=0;
	private static BluetoothServerSocket mserverSocket = null;
	private ServerThread startServerThread = null;
	private clientThread clientConnectThread = null;
	private static BluetoothSocket socket = null;
	private BluetoothDevice device = null;
	private readThread mreadThread = null;;	
	private BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    static String BlueToothAddress = "null";
    static ServerOrCilent serviceOrCilent = ServerOrCilent.NONE;
    static boolean isOpen = false;
 BluetoothAdapter mBtAdapter = BluetoothAdapter.getDefaultAdapter();
 ListView mListView;
ImageButton seachButton, serviceButton,passButton,enter;
Context mContext;
SimpleAdapter listItemAdapter;
 ArrayList<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();
private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        // When discovery finds a device
        if (BluetoothDevice.ACTION_FOUND.equals(action)) 
        {
            // Get the BluetoothDevice object from the Intent
            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            // If it's already paired, skip it, because it's been listed already
            if (device.getBondState() != BluetoothDevice.BOND_BONDED) 
            {
            	 HashMap<String, String> map = new HashMap<String, String>();  
                // map.put("ItemImage", R.drawable.checked);//图像资源的ID  
                 map.put("ItemTitle", device.getName());  
                 map.put("ItemText", device.getAddress());
                
                 System.out.println("蓝牙="+device.getName()+" "+device.getAddress());
                 list.add(map);  
            	listItemAdapter.notifyDataSetChanged();
            	 if(!deviceArray.contains(device.getName()))
                 {
            		 BluetoothDevice btDev = mBluetoothAdapter.getRemoteDevice(device.getAddress()); 
                     try { 
                         Boolean returnValue = false; 
                         if (btDev.getBondState() == BluetoothDevice.BOND_NONE) { 
                             Toast.makeText(bluetooth.this, "远程设备发送蓝牙配对请求", 5000).show(); 
                             //这里只需要createBond就行了
                          ClsUtils.createBond(btDev.getClass(), btDev);   
                         }else if(btDev.getBondState() == BluetoothDevice.BOND_BONDED){ 
                             Toast.makeText(bluetooth.this, btDev.getBondState()+" ....正在连接..", 1000).show(); 
                         } 
                     } catch (Exception e) { 
                         e.printStackTrace(); 
                     } 
                 }
        		//mListView.setSelection(list.size() - 1);
            }
            mListView.setAdapter(listItemAdapter);
            mListView.setOnItemClickListener(mDeviceClickListener);	
        // When discovery is finished, change the Activity title
        } 
        else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) 
        {
            setProgressBarIndeterminateVisibility(false);
           
            //	mAdapter.notifyDataSetChanged();
        		//mListView.setSelection(list.size() - 1);
           // seachButton.setText("重新搜索");
        }
    }
};	
/*
public synchronized void onResume() {
    super.onResume();
    if(isOpen)
    {
    	Toast.makeText(mContext, "连接已经打开，可以通信。如果要再建立连接，请先断开！", Toast.LENGTH_SHORT).show();
    	return;
    }
    if(serviceOrCilent==ServerOrCilent.CILENT)
    {
		String address = BlueToothAddress;
		if(!address.equals("null"))
		{
			device = mBluetoothAdapter.getRemoteDevice(address);	
			clientConnectThread = new clientThread();
			clientConnectThread.start();
			isOpen = true;
		}
		else
		{
			Toast.makeText(mContext, "address is null !", Toast.LENGTH_SHORT).show();
		}
    }
    else if(serviceOrCilent==ServerOrCilent.SERVICE)
    {        	      	
    	startServerThread = new ServerThread();
    	startServerThread.start();
    	isOpen = true;
    }
}
*/
    @Override
    public void onStart() {
        super.onStart();
        // If BT is not on, request that it be enabled.
        if (!mBtAdapter.isEnabled()) {
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            //startActivityForResult(enableIntent, 3);
            enableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
        	startActivity(enableIntent);
        }
    }
    public static void sendMessage(String info)
    {
    	System.out.println("在sendMessage中info="+info);
    	if(socket==null)
    	{
    		try {
				socket = mserverSocket.accept();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	if(info!=null)
    	{
    		try {				
			OutputStream os = socket.getOutputStream(); 
			os.write(info.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
    	}
    	
    }
    static void trans(String info)
    {
    	System.out.println("在bluetooth中info="+info);
    	if(serviceOrCilent==ServerOrCilent.CILENT)
		{
			//sendMessageHandle("client"+i++);	
			Message msg2 = new Message();
			//String info ="client"+i++;
			msg2.obj = info;
			//LinkDetectedHandler.sendMessage(msg2);
			sendMessage(info); 
			
		}
		else if(serviceOrCilent==ServerOrCilent.SERVICE)
		{
			Message msg2 = new Message();
			//String info ="server"+i++;
			msg2.obj = info;
			//LinkDetectedHandler.sendMessage(msg2);
			sendMessage(info);
		}
		else
		{
			System.out.println("蓝牙装置没有连接上");
		}
	
    }
    public void getBondDevice()
    {
  	  set=mBluetoothAdapter.getBondedDevices();
  	  Iterator<BluetoothDevice> it=set.iterator();
  	  System.out.println("set.size()="+set.size());
  	  while(it.hasNext()&&set.size()>0)
  	  {
  		  BluetoothDevice  device=it.next();
  		//  String s=device.getName()+" "+device.getAddress();
  		  String s=device.getName();
  		  System.out.println("s="+s);
  		  deviceArray.add(s);
  	  }
    }
	public void  onCreate(Bundle savedInstanceState)
	{
		
		super.onCreate(savedInstanceState);
		DisplayMetrics metric=new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metric);
		if(metric.widthPixels>1200)
		{
			setContentView(R.layout.devices);
			Log.e("LayoutChoice","R.layout.devices");
		}
		else if(metric.widthPixels>900)
		{
			 setContentView(R.layout.devices960_540);
			 Log.e("LayoutChoice","R.layout.devices960_540");
		}
		else 
		{
			setContentView(R.layout.devices480_800);
			Log.e("LayoutChoice","R.layout.devices480_800");
		}
		  typeFace = Typeface.createFromAsset(getAssets(),"girl.TTF");
		  t1=(TextView)findViewById(R.id.ItemTitle);
		  t2=(TextView)findViewById(R.id.ItemText);
		  getBondDevice();
		 // t1.setTypeface(typeFace);
		 // t2.setTypeface(typeFace);
		 /*
		 passButton=(Button)findViewById(R.id.pass);
		 passButton.setOnClickListener(new OnClickListener()
		 {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				System.out.println("发送数据");
				//Looper.prepare();
				if(serviceOrCilent==ServerOrCilent.CILENT)
				{
					//sendMessageHandle("client"+i++);	
					Message msg2 = new Message();
					String info ="client"+i++;
					msg2.obj = info;
					//LinkDetectedHandler.sendMessage(msg2);
					sendMessage(info); 
					c
				}
				else if(serviceOrCilent==ServerOrCilent.SERVICE)
				{
					Message msg2 = new Message();
					String info ="server"+i++;
					msg2.obj = info;
					//LinkDetectedHandler.sendMessage(msg2);
					sendMessage(info);
				}
				else
				{
					System.out.println("蓝牙装置没有连接上");
				}
			}
			 
		 });
		 */
		 enter=(ImageButton)findViewById(R.id.enter);
		// enter.setPaddingRelative(50, 0, 50, 10);
		
		 enter.setOnClickListener(new OnClickListener()
		 {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(bluetooth.this,blueMainActivity.class);
				startActivity(intent);
			}
			 
		 });
		 mListView=(ListView)findViewById(R.id.ListView);
	
	        mContext = this;
	        


			
	    	        listItemAdapter = new SimpleAdapter(this,list,  
		            R.layout.list_items,//ListItem的XML实现  
		            //动态数组与ImageItem对应的子项          
		            new String[] {"ItemTitle", "ItemText"},   
		            //ImageItem的XML文件里面的一个ImageView,两个TextView ID  
		            new int[] {R.id.ItemTitle,R.id.ItemText}  
		        );  
		      
   List<String> data = new ArrayList<String>();
   data.add("测试数据1");
   data.add("测试数据2");
   data.add("测试数据3");
   data.add("测试数据4");
   
	    	//mListView.setAdapter(listItemAdapter); 
	    //	mListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,data));
	       init();
	}
	public void init()
	{
		
		// HashMap<String, String> map = new HashMap<String, String>();  
         // map.put("ItemImage", R.drawable.checked);//图像资源的ID  
       //   map.put("ItemTitle", "a");  
        //  map.put("ItemText", "d");  
        //  list.add(map);  
	
	         
	        //添加并且显示  
		
		IntentFilter discoveryFilter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
	        this.registerReceiver(mReceiver, discoveryFilter);

	        // Register for broadcasts when discovery has finished
	        IntentFilter foundFilter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
	        this.registerReceiver(mReceiver, foundFilter);
	        Set<BluetoothDevice> pairedDevices = mBtAdapter.getBondedDevices();

	        // If there are paired devices, add each one to the ArrayAdapter
	        if (pairedDevices.size() > 0) {
	            for (BluetoothDevice device : pairedDevices) 
	            {
	            	HashMap<String, String> map = new HashMap<String, String>();  
	                 map.put("ItemTitle",device.getName());  
	                 map.put("ItemText",device.getAddress());  
	                 list.add(map);  
	            	 listItemAdapter .notifyDataSetChanged();
	            	 
	        		mListView.setSelection(list.size() - 1);
	            }
	       }
	        //搜索按钮
	        seachButton = (ImageButton)findViewById(R.id.start_seach);
			seachButton.setOnClickListener(seachButtonClickListener);
			serviceButton = (ImageButton)findViewById(R.id.start_service);
			serviceButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					serviceOrCilent=ServerOrCilent.SERVICE;
					if(serviceOrCilent==ServerOrCilent.CILENT)
				    {
						System.out.println("客户端启动");
						String address = BlueToothAddress;
						if(!address.equals("null"))
						{
							device = mBluetoothAdapter.getRemoteDevice(address);	
							clientConnectThread = new clientThread();
							clientConnectThread.start();
							isOpen = true;
						}
						else
						{
							Toast.makeText(mContext, "address is null !", Toast.LENGTH_SHORT).show();
						}
				    }
				    else if(serviceOrCilent==ServerOrCilent.SERVICE)
				    {        
				    	Toast.makeText(getApplication(),"服务端启动", Toast.LENGTH_SHORT).show();
				    	System.out.println("服务端启动");
				    	startServerThread = new ServerThread();
				    	startServerThread.start();
				    	isOpen = true;
				    }
				}
			});
}

 OnClickListener seachButtonClickListener = new OnClickListener() {
	 @Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
		 	if(mBtAdapter.isDiscovering()) 
		 	{
		 		mBtAdapter.cancelDiscovery();
		 		
		 	}
		 	else
		 	{
		 		list.clear();
				listItemAdapter.notifyDataSetChanged();
				
				Set<BluetoothDevice> pairedDevices = mBtAdapter.getBondedDevices();
				if (pairedDevices.size() > 0) {
			            for (BluetoothDevice device : pairedDevices) {
			            	HashMap<String, String> map = new HashMap<String, String>();  
			                 map.put("ItemTitle",device.getName());  
			                 map.put("ItemText",device.getAddress());  
			                 list.add(map);  
			            	 listItemAdapter .notifyDataSetChanged();
			            	 mListView.setAdapter(listItemAdapter);
			        		mListView.setSelection(list.size() - 1);
			            }
			    } else {
			        	
			    	listItemAdapter .notifyDataSetChanged();
			    		mListView.setSelection(list.size() - 1);
			     }					
		        /* 开始搜索 */
				mBtAdapter.startDiscovery();
				
		 	}				 
		}
	};
	
	//开启客户端
		private class clientThread extends Thread { 		
			public void run() {
				try {
					//创建一个Socket连接：只需要服务器在注册时的UUID号
					// socket = device.createRfcommSocketToServiceRecord(BluetoothProtocols.OBEX_OBJECT_PUSH_PROTOCOL_UUID);
					socket = device.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
					//连接
					Message msg2 = new Message();
					msg2.obj = "请稍候，正在连接服务器:"+BlueToothAddress;
					
					msg2.what = 0;
					//Toast.makeText(getApplication(),"请稍候，正在连接服务器:"+BlueToothAddress, Toast.LENGTH_SHORT).show();
					LinkDetectedHandler.sendMessage(msg2);
					
					socket.connect();
					
					Message msg = new Message();
					msg.obj = "已经连接上服务端！可以开始游戏。";
					msg.what = 0;
					LinkDetectedHandler.sendMessage(msg);
					//Toast.makeText(getApplication(),"已经连接上服务端！可以开始游戏。", Toast.LENGTH_SHORT).show();
					//启动接受数据
					mreadThread = new readThread();
					mreadThread.start();
				} 
				catch (IOException e) 
				{
					Log.e("connect", "", e);
					Message msg = new Message();
					msg.obj = "连接服务端异常！断开连接重新试一试。";
					msg.what = 0;
					LinkDetectedHandler.sendMessage(msg);
				} 
			}
		};

		//开启服务器
		private class ServerThread extends Thread { 
			public void run() {
						
				try {
					/* 创建一个蓝牙服务器 
					 * 参数分别：服务器名称、UUID	 */	
					mserverSocket = mBluetoothAdapter.listenUsingRfcommWithServiceRecord(PROTOCOL_SCHEME_RFCOMM,
							UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));		
					
					Log.d("server", "wait cilent connect...");
					
					Message msg = new Message();
					msg.obj = "请稍候，正在等待客户端的连接...";
					msg.what = 0;
					LinkDetectedHandler.sendMessage(msg);
					//Toast.makeText(this.,"请稍候，正在等待客户端的连接...", Toast.LENGTH_SHORT).show();
					/* 接受客户端的连接请求 */
					socket = mserverSocket.accept();
					Log.d("server", "accept success !");
					
					Message msg2 = new Message();
					String info = "客户端已经连接上！可以开始游戏。";
					msg2.obj = info;
					msg.what = 0;
					LinkDetectedHandler.sendMessage(msg2);
					//Toast.makeText(getApplication(),"客户端已经连接上！可以开始游戏。", Toast.LENGTH_SHORT).show();
					//启动接受数据
					mreadThread = new readThread();
					mreadThread.start();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		/* 停止服务器 */
		private void shutdownServer() {
			new Thread() {
				public void run() {
					if(startServerThread != null)
					{
						startServerThread.interrupt();
						startServerThread = null;
					}
					if(mreadThread != null)
					{
						mreadThread.interrupt();
						mreadThread = null;
					}				
					try {					
						if(socket != null)
						{
							socket.close();
							socket = null;	
						}
						if (mserverSocket != null)
						{
							mserverSocket.close();/* 关闭服务器 */
							mserverSocket = null;
						}
					} catch (IOException e) {
						Log.e("server", "mserverSocket.close()", e);
					}
				};
			}.start();
		}
		/* 停止客户端连接 */
		private void shutdownClient() {
			new Thread() {
				public void run() {
					if(clientConnectThread!=null)
					{
						clientConnectThread.interrupt();
						clientConnectThread= null;
					}
					if(mreadThread != null)
					{
						mreadThread.interrupt();
						mreadThread = null;
					}
					if (socket != null) {
						try {
							socket.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						socket = null;
					}
				};
			}.start();
		}
		//发送数据
		private void sendMessageHandle(String msg) 
		{		
			if (socket == null) 
			{
				Toast.makeText(mContext, "没有连接", Toast.LENGTH_SHORT).show();
				return;
			}
			try {				
				OutputStream os = socket.getOutputStream(); 
				os.write(msg.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			//list.add(new deviceListItem(msg, false));
			//mAdapter.notifyDataSetChanged();
			//mListView.setSelection(list.size() - 1);
		}
		//读取数据
	    private class readThread extends Thread { 
	        public void run() {
	        	System.out.println("开启从其余线程读取数据");
	            byte[] buffer = new byte[1024];
	            int bytes;
	            InputStream mmInStream = null;
	            
				try {
					mmInStream = socket.getInputStream();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
	            while (true) {
	                try {
	                	
	                    // Read from the InputStream
	                    if( (bytes = mmInStream.read(buffer)) > 0 )
	                    {
	                    	System.out.println("开启从其余线程读取数据");
		                    byte[] buf_data = new byte[bytes];
					    	for(int i=0; i<bytes; i++)
					    	{
					    		buf_data[i] = buffer[i];
					    	}
							String s = new String(buf_data);
							receive=s;
							System.out.println("从数据流中读取数据为"+s);
							//Toast.makeText(getApplication(), "从数据流中读取数据为"+s,Toast.LENGTH_LONG).show();
							Message msg = new Message();
							msg.obj = s;
							msg.what = 1;
							LinkDetectedHandler.sendMessage(msg);
	                    }
	                } catch (IOException e) {
	                	try {
							mmInStream.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	                    break;
	                }
	            }
	        }
	    }
	    private Handler LinkDetectedHandler = new Handler() {
	        @Override
	        public void handleMessage(Message msg) {
	        	//Toast.makeText(mContext, (String)msg.obj, Toast.LENGTH_SHORT).show();
	        	System.out.println("已经收到数据");
	        	System.out.println("处理数据为"+(String)msg.obj);
	        	String x=(String)msg.obj;
	        	if(x.length()==2)
	        	receive=(String)msg.obj;
	        	
	        	
	        	if(receive!=null)
	        	{   System.out.println(receive);
	        		System.out.println(receive);
	        		if(receive.contains("w")||receive.contains("h"))
		        	{
	        			System.out.println("从另一台机器接收数据为");
		        		//if(receive.contains("w"))
		        		//{
		        			String k[]=receive.split(" ");
		        			w=Float.parseFloat(k[1]);
		        			System.out.println("在biuetooth中收到w="+w);
		        		//}
		        	///	if(receive.contains("h"))
		        		//{
		        			//String k[]=receive.split(" ");
		        			h=Float.parseFloat(k[3]);
		        			System.out.println("在biuetooth中收到h="+h);
		        		//}
		        	}
	        	}
	        	
	        	//if(receive.equals("ready"))
	        	//{
	        		state=(String)msg.obj;
	        	//}
	        	
	        	int m=0;
	        	try
	        	{
	        		m=Integer.parseInt((String)msg.obj);
	        	}
	        	catch(Exception e)
	        	{
	        		System.out.println("数字格式错误");
	        	}
	        	if(m>10000)
	        	{
	        		blockreceice=(String)msg.obj;
	        		System.out.println("远程");
	        		blueFirstGame.move(blockreceice);
	        	}
	        	System.out.println("在bluetooth中"+(String)msg.obj+"state="+state);
	        	String mm=(String)msg.obj;
	        	if(mm.length()>5)
	        	Toast.makeText(getApplication(), (String)msg.obj,Toast.LENGTH_SHORT).show();
				//mAdapter.notifyDataSetChanged();
				//mListView.setSelection(list.size() - 1);
	        }
	        
	    };    
	    private OnItemClickListener mDeviceClickListener = new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> av, View v, int arg2, long arg3) {
	            // Cancel discovery because it's costly and we're about to connect     
	        	
	        	HashMap<String,String> item = list.get(arg2);
	        	
	            String info = item.get("ItemText");
	            String address = info.substring(info.length() - 17);                 
	            BlueToothAddress = address;
	            
	             final AlertDialog dig = new AlertDialog.Builder(mContext).create();//定义一个弹出框对象
	             //StopDialog.setTitle("连接");//标题       
	            dig.show();
	        	 Window window=dig.getWindow();
	        	 window.setContentView(R.layout.ld);
	        	 TextView img=(TextView)window.findViewById(R.id.img);
		        img.setTypeface(Typeface.createFromAsset(getAssets(),"girl.TTF"));
	        	 TextView enter=(TextView)window.findViewById(R.id.ld_enter);
	        	 enter.setTypeface(Typeface.createFromAsset(getAssets(),"girl.TTF"));
	        	 enter.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						mBtAdapter.cancelDiscovery();
	            	    
		                 dig.hide();
		            	 serviceOrCilent=ServerOrCilent.CILENT;
		            	 if(serviceOrCilent==ServerOrCilent.CILENT)
						    {
								System.out.println("客户端启动");
								Toast.makeText(getApplication(),"客户端启动", Toast.LENGTH_SHORT).show();
								String address = BlueToothAddress;
								if(!address.equals("null"))
								{
									device = mBluetoothAdapter.getRemoteDevice(address);	
									clientConnectThread = new clientThread();
									clientConnectThread.start();
									isOpen = true;
								}
								else
								{
									Toast.makeText(mContext, "address is null !", Toast.LENGTH_SHORT).show();
								}
						    }
						    else if(serviceOrCilent==ServerOrCilent.SERVICE)
						    {        
						    	System.out.println("服务端启动");
						    	startServerThread = new ServerThread();
						    	startServerThread.start();
						    	isOpen = true;
						    }
		                
						
					}
				});
	        	 TextView cl=(TextView)window.findViewById(R.id.ld_ce);
	        	 cl.setTypeface(Typeface.createFromAsset(getAssets(),"girl.TTF"));
	        	 cl.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						BlueToothAddress = null;
						dig.cancel();
						
					}
				});
	        	 /*StopDialog.setMessage(item.get("ItemText"));
	             StopDialog.setView(myView);
	             StopDialog.setPositiveButton("连接", new DialogInterface.OnClickListener() {  
	             public void onClick(DialogInterface dialog, int which) {  
	                 // TODO Auto-generated method stub   
	            	 mBtAdapter.cancelDiscovery();
	            	    
	                 
	            	 serviceOrCilent=ServerOrCilent.CILENT;
	            	 if(serviceOrCilent==ServerOrCilent.CILENT)
					    {
							System.out.println("客户端启动");
							Toast.makeText(getApplication(),"客户端启动", Toast.LENGTH_SHORT).show();
							String address = BlueToothAddress;
							if(!address.equals("null"))
							{
								device = mBluetoothAdapter.getRemoteDevice(address);	
								clientConnectThread = new clientThread();
								clientConnectThread.start();
								isOpen = true;
							}
							else
							{
								Toast.makeText(mContext, "address is null !", Toast.LENGTH_SHORT).show();
							}
					    }
					    else if(serviceOrCilent==ServerOrCilent.SERVICE)
					    {        
					    	System.out.println("服务端启动");
					    	startServerThread = new ServerThread();
					    	startServerThread.start();
					    	isOpen = true;
					    }
	                
	             }  
	             });
	             StopDialog.setNegativeButton("取消",new DialogInterface.OnClickListener() {                       
	                 public void onClick(DialogInterface dialog, int which) {  
	                	 BlueToothAddress = null;
	                 }
	             });
	             StopDialog.show();   */                         
	        }
	    };
	    @Override
	    protected void onDestroy() {
	        super.onDestroy();

	        //if (serviceOrCilent == ServerOrCilent.CILENT) 
			//{
	        	//shutdownClient();
			//}
			//else if (serviceOrCilent == ServerOrCilent.SERVICE) 
			//{
			//	shutdownServer();
			//}
	        isOpen = false;
	        this.unregisterReceiver(mReceiver);
			//serviceOrCilent = ServerOrCilent.NONE;
	    }
}