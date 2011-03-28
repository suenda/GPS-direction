package model.base;


import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;

import model.rawData.RawRoute;
import model.rawData.Route;
import model.rawData.Station;
import model.skeleton.Ligne;

import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import exception.InvalidRouteArgument;

public class DOMSerializer {
	
	List<Ligne> transport = new ArrayList<Ligne>();
	List<Station> stations = new ArrayList<Station>();

	List<RawRoute> rawRoutes = new ArrayList<RawRoute>();
	List<Route> routes = new ArrayList<Route>();
	
	String lgname, code, type, start, end;
	
	public DOMSerializer( ) {

	}


	public void serialize(Document doc)	{
		// Start serialization recursion with no indenting
		serializeNode(doc);
	}
	
	public void serializeNode(Node node){

//		System.out.println("Now serializing: " + node.getNodeName());
		switch (node.getNodeType( )) {
			case Node.DOCUMENT_NODE:
				
				// recurse on each child
				NodeList nodes = node.getChildNodes( );
				if (nodes != null) {
					for (int i=0; i<nodes.getLength( ); i++) {
						serializeNode(nodes.item(i));
					}
				}
				break;

			case Node.ELEMENT_NODE:
				String name = node.getNodeName();

				if(name=="mapdata") {
					NodeList children = node.getChildNodes( );
					if (children != null) {						
						for (int i=0; i<children.getLength( ); i++) {
							serializeNode(children.item(i));
						}
					}
				}
				
				else if (name=="transport") {
					NodeList children = node.getChildNodes( );
					if (children != null) {						
						for (int i=0; i<children.getLength( ); i++) {
							serializeNode(children.item(i));
						}
					}
				}
				
				else if (name=="ligne") {
					NamedNodeMap attributes = node.getAttributes();
					

					for (int i=0; i<attributes.getLength( ); i++) {
						Node current = attributes.item(i);
						
						if(current.getNodeName()=="name") {
							lgname = current.getNodeValue();
						} else if (current.getNodeName()=="type") {
							type = current.getNodeValue();
						}
						
					
					}
					transport.add(new LigneGen(type, lgname));
					System.out.println("Ligne added -> Name: " + lgname + " type: " + type);


					
				}
				else if (name=="stations") {
					
					NodeList children = node.getChildNodes( );
					if (children != null) {						
						for (int i=0; i<children.getLength( ); i++) {
							serializeNode(children.item(i));
						}
					}
				}
				
				else if (name=="station") {
					NamedNodeMap attributes = node.getAttributes();


					
					for (int i=0; i<attributes.getLength( ); i++) {
						Node current = attributes.item(i);
						
						if(current.getNodeName()=="code") {
							code = current.getNodeValue();
						} else if (current.getNodeName()=="xcor") {
							start = current.getNodeValue();
						} else if (current.getNodeName()=="ycor") {
							end = current.getNodeValue();
						}

					}
					
					int xx = 25 * Integer.parseInt(start);
					int yy = 25 * Integer.parseInt(end);
					
					stations.add(new Station(code, xx, yy));
					System.out.println("Station added-> Name: " + code + " start: " + start + " end: " + end);

				}
				
				else if (name=="routes") {
					
					NodeList children = node.getChildNodes( );
					if (children != null) {						
						for (int i=0; i<children.getLength( ); i++) {
							serializeNode(children.item(i));
						}
					}
				}
				
				else if (name=="route") {
					NamedNodeMap attributes = node.getAttributes();
					
					for (int i=0; i<attributes.getLength( ); i++) {
						Node current = attributes.item(i);
						
						if(current.getNodeName()=="code") {
							code = current.getNodeValue();
						} else if (current.getNodeName()=="type") {
							type = current.getNodeValue();
						} else if (current.getNodeName()=="ligne") {
							lgname = current.getNodeValue();
						} else if (current.getNodeName()=="sstation") {
							start = current.getNodeValue();
						} else if (current.getNodeName()=="estation") {
							end = current.getNodeValue();
						}
						
					}
					rawRoutes.add(new RawRoute(code, type, lgname, start, end));
					System.out.println("Route added -> Name: " + code + " type: " + type + " ligne:"+ lgname + " start: " + start + " end: " + end);

				}
			




				break;

			case Node.TEXT_NODE:
				System.out.println(node.getNodeValue( ));
				break;
				
			case Node.CDATA_SECTION_NODE:
				System.out.println("<![CDATA[" +
						node.getNodeValue( ) + "]]>");
				break;
			
			case Node.COMMENT_NODE:
				System.out.println("\t" + "<!-- " +
						node.getNodeValue( ) + " -->");
						System.out.println("\n");	
				break;
			
			case Node.PROCESSING_INSTRUCTION_NODE:
				System.out.println("<?" + node.getNodeName( ) +
						" " + node.getNodeValue( ) +
						"?>");
				System.out.println("\n");
				break;
			
			case Node.ENTITY_REFERENCE_NODE:
				System.out.println("&" + node.getNodeName( ) + ";");	
				break;
			
			case Node.DOCUMENT_TYPE_NODE:
				DocumentType docType = (DocumentType)node;
				System.out.println("<!DOCTYPE " + docType.getName( ));

				if (docType.getPublicId( ) != null) {
					System.out.print(" PUBLIC \"" +
					docType.getPublicId( ) + "\" ");
				} else {
				System.out.println(" SYSTEM ");
				}
				System.out.println("\"" + docType.getSystemId( ) + "\">");
				System.out.println("\n");
				break;
		}
		
	}

	public List<Ligne> cookRoute() {
		
		Station s=null, e=null;
		
		Iterator<Station> itr = stations.iterator();
		String[] stationName = new String[stations.size()];
		
		Iterator<Ligne> itln = transport.iterator();
		String[] ligneName = new String[transport.size()];
		int c = 0;
	
		
		while(itr.hasNext()) {
			stationName[c] = itr.next().getName();
			c++;
		}
		c=0;
		
		while(itln.hasNext()){
			ligneName[c] = itln.next().getName();
			c++;
		}
		
		Iterator<RawRoute> itrr = rawRoutes.iterator();
		while(itrr.hasNext()) {
;
			RawRoute rr = itrr.next();
			
			for(int i = 0; i<stationName.length; i++) {
				
				if(stationName[i].equalsIgnoreCase(rr.getsStation())) {
					 s = stations.get(i);
				} 
				if(stationName[i].equalsIgnoreCase(rr.geteStation())) {
					 e = stations.get(i);
				}
				
			}
			for(int j=0; j<ligneName.length; j++) {
				String s1 = ligneName[j];
				String s2 = rr.getLigne();
				
				if(s1.equalsIgnoreCase(s2)) {
					
					System.out.println(transport.get(j).getName() + "of type " + transport.get(j).getType() + " is adding " + rr.getCode() + " of ligne:" + rr.getLigne()+ "  <==> " + ligneName[j]);
						transport.get(j).addRoute(new Route(rr.getCode(), rr.getType(), rr.getLigne(), s, e));
						System.out.println(s.toString() + "\t" + e.toString());
						System.out.println(transport.get(j).getName() + " added " + rr.getCode());
			
				}
			}
			
			
		}
		return transport;
		
		
		
	}
	



}
