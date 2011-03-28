package build;


import java.awt.Color;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.JFrame;

import model.base.MapBase;
import model.base.SearchCore;
import model.rawData.Route;
import model.rawData.RouteQueue;
import model.rawData.Station;
import model.searchFeature.OptimizedCost;
import model.searchFeature.OptimizedDistance;
import model.searchFeature.OptimizedTime;
import model.searchFeature.PreferTransport;
import model.skeleton.Ligne;
import model.skeleton.Map;
import model.skeleton.Search;
import model.skeleton.SearchFilter;
import view.graphics.MapDraw;
import view.ui.UserUI;
import exception.CorruptData;
import exception.InvalidRouteArgument;




@SuppressWarnings("serial")
public class MapTest extends UserUI {
	
	private Map mp = new MapBase("/home/surendra/Files/Mapdata.xml");
	private Search searchMap = SearchCore.getInstance();
	
	MapDraw dr = new MapDraw();

	private ArrayList<String> uiPreference = new ArrayList<String>();
	private Station uiStartPoint=null;
	private Station uiEndPoint=null;
	
	List<RouteQueue> resultRoutes = new ArrayList<RouteQueue>();


	ExecutorService threadExecutor = Executors.newFixedThreadPool(1);
	

	public MapTest() {
		threadExecutor.execute((Runnable) mp);
	}
	


	

	public void showUI() {


		JFrame frame = new JFrame();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   	frame.getContentPane().add(this);
	   	frame.pack();
    	frame.setVisible(true);
    	
    }


	public void draw() {
		dr.drawMap(mp, Color.blue);
	}
	
	public void testRouteQueue() {
		RouteQueue testrQ = new RouteQueue();

		ArrayList<Ligne> lns = (ArrayList<Ligne>) mp.getLignes();
		ArrayList<Route> rts = (ArrayList<Route>) lns.get(2).getRoutes();
		
		for(int t = 0; t<rts.size(); t++) {
			testrQ.add(rts.get(t));
		}
		
		dr.drawRoutes(testrQ, Color.red);

	
	}

	@SuppressWarnings("unused")
	private void setUIEvents() {
		uiResetBut.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent evt) {
	            buttonAction(evt);
	        }
	    });
		
		uiSearchBut.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent evt) {
	            buttonAction(evt);
	        }
	    });
		
	     uiCostCB.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent evt) {
		        	checkBoxAction(evt);
		        }
		    });
	     uiFootCB.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent evt) {
		        	checkBoxAction(evt);
		        }
		    });
	     uiTimeCB.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent evt) {
		        	checkBoxAction(evt);
		        }
		    });
	     uiDistanceCB.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent evt) {
		        	checkBoxAction(evt);
		        }
		    });
	     uiBusCB.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent evt) {
		        	checkBoxAction(evt);
		        }
		    });
	     uiTrainCB.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent evt) {
		        	checkBoxAction(evt);
		        }
		    });
	     uiWaterCB.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent evt) {
		        	checkBoxAction(evt);
		        }
		    });
	     uiAirCB.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent evt) {
		        	checkBoxAction(evt);
		        }
		    });
	     uiAutoCB.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent evt) {
		        	checkBoxAction(evt);
		        }
		    });
	     uiCycleCB.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent evt) {
		        	checkBoxAction(evt);
		        }
		    });

	}
	

	
	private void buttonAction(ActionEvent evt) {

		if(evt.getSource()==uiResetBut){
			uiStartPointxField.setText("");
			uiStartPointyField.setText("");
			uiEndPointxField.setText("");
			uiEndPointyField.setText("");
		}
		
		if(evt.getSource()==uiSearchBut){
			try {
				String x = uiStartPointxField.getText();
				String y = uiStartPointyField.getText();
				uiStartPoint = new Station("", Integer.valueOf(x), Integer.valueOf(y));
				x = uiEndPointxField.getText();
				y = uiEndPointyField.getText();
				uiEndPoint = new Station("", Integer.valueOf(x), Integer.valueOf(y));
				
			} catch (NumberFormatException e) {
				
				System.out.println("Enter valid points");
			} finally {
				performSearchOperation();
			}
			
		}
		
		
	}

	private void checkBoxAction(ActionEvent evt) {
	       	
	        AbstractButton checkbox = (AbstractButton) evt.getSource();
	        
	        
	        boolean state = checkbox.getModel().isSelected();
	        if(state){
	        	uiPreference.add(checkbox.getActionCommand());
	        } else {
	        	if(uiPreference.contains(checkbox.getActionCommand())) {
	        		uiPreference.remove(checkbox.getActionCommand());
	        	}
	        }

	        
	        
	        
	    }
	
	private void performSearchOperation() {


			resultRoutes = searchMap.searchPath(uiStartPoint, uiEndPoint, mp.getAllMapRoutes());
			
			//filter the path returned by the search method
			Iterator<String>itr = uiPreference.iterator();
			String prefer;
			SearchFilter sf;
			
		while(itr.hasNext()) {
				prefer = itr.next();
				if(prefer=="Optimized Cost") {
					sf = new OptimizedCost();
					resultRoutes = sf.filter(resultRoutes);
				}
				else if(prefer=="Shortest Time") {
					sf = new OptimizedTime();
					resultRoutes = sf.filter(resultRoutes);
				}
				else if(prefer=="Shortest Distance") {
					sf = new OptimizedDistance();
					resultRoutes = sf.filter(resultRoutes);

				} else {
					sf = new PreferTransport(prefer);
					resultRoutes = sf.filter(resultRoutes);
				}

							
			}//end of while //end of filter of search results
			


			
	 }
	
	public void testSearch() {
		RouteQueue rq = new RouteQueue();
		rq.add(mp.getAllMapRoutes().get(10));
		dr.drawRoutes(rq, Color.green);
	}
	
	public static void main(String[] args) throws CorruptData, InvalidRouteArgument {
		MapTest test = new MapTest();

		test.draw();
		test.showUI();
		test.testSearch();

	}
	
}
