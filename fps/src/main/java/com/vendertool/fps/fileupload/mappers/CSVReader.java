package com.vendertool.fps.fileupload.mappers;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.lf5.LogLevel;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.exception.SuperCsvException;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

/*public class CSVReader {

    private ICsvBeanReader beanReader = null;
    private String[] header;
    private CellProcessor[] processors;


    public CSVReader(InputStream inputStream) {
        try {
            
            beanReader = new CsvBeanReader(new InputStreamReader(inputStream),CsvPreference.STANDARD_PREFERENCE);
            header = beanReader.getHeader(true);
            processors = getProcessors();
            nextDetail();
         }
        catch (IOException e) {
                       
        }
    }

    public ProductBean nextDetail() {
        ProductBean record = null;
        try {
            
            record = beanReader.read(ProductBean.class, header, processors);

            if (record != null) {
                record = beanReader
                        .read(ProductBean.class, header, processors);
            } else {
            	if (beanReader != null) {
            		beanReader.close();
            	}
            }
        } catch (IOException e) {
        }
        
        return record;
    }

    @Override
    public boolean hasMoreDetail() {
        if (currentObject == null) {
            return false;
        } else {
            return true;
        }

    }

    @Override
    public HeaderRecord getHeaderRecord() {
        try {
            read();
        } catch (IOException e) {
            CalEventHelper.writeLog("BES_SHPMT_TRACKG_DOWNLOAD", "PushbackUKReturnsFileReader.getHeaderRecord","IOException",e,true);
        }
        
        return null;
    }

    private CellProcessor[] getProcessors() {
        final CellProcessor[] processors = new CellProcessor[] {
                new Optional(), new Optional(), new Optional(), new Optional(),
                new Optional(), new Optional(), new Optional(), new Optional(),
                new Optional(), new Optional(), new Optional(), new Optional(),
                new Optional(), new Optional(), new Optional(), new Optional(),
                new Optional(), new Optional(), new Optional(), new Optional(),
                new Optional(), new Optional(), new Optional(), new Optional(),
                new Optional(), new Optional()

        };

        return processors;
    }

}
*/

/*
public class CSVReader {
    private ICsvBeanReader beanReader = null;
    private String[] header;
    private CellProcessor[] processors;
    private String[] dynamicHeader;
    
	public boolean processFile(String filePath) {                              
                     
		ICsvBeanReader beanReader = null;                         
              
		try {                                        
			InputStream reader = new BufferedInputStream(new FileInputStream((new File(filePath))));                       
 			beanReader = new CsvBeanReader(new InputStreamReader(reader), CsvPreference.STANDARD_PREFERENCE);                                   
			String[] headerInFile = beanReader.getHeader(true);                                   
			                                                
			dynamicHeader = new String[headerInFile.length];                                       
			processors = new CellProcessor[headerInFile.length];                                  
                                         
			int colCnt = 0;                                      
			for (String header: headerInFile) {                                                           
 				dynamicHeader[colCnt] = header;                                                                              
				processors[colCnt++] = new Optional();                                                         
			}                                              
		} catch (Exception e) {                                    
                         
		}                              
	}              
	
	private T readDataFromFile() throws SuperCsvException,IOException {                   
       
		T trackingData;                  
		try {                                        
			while( (trackingData = beanReader.read(<T>, header, processors)) != null ) {                                                               
				if (trackingData != null && trackingData.getCountry_Consignment_Number() != null) {                                                                 
					tntTrackingDataList.add(trackingData);                                                 
				}                                              
			}                              
		}catch (SuperCsvException e) {                                  
			cal.markCAL("Invalid Record in the file: " + filePath);                                     
			cal.markCAL(e);                                               
			cal.closeCAL();                                  
			readDataFromFile(beanReader, header,processors,tntTrackingDataList, filePath);                            
		}catch (Exception e) {                                      
			cal.markCAL(e);                                                
			cal.closeCAL();                  
		} finally {                                              
			if( beanReader != null ) {                                                   
				try {                                                                        
					beanReader.close();                                                      
				} catch (IOException e) {                                                                  
					// TODO Auto-generated catch block                                                                     
					e.printStackTrace();                                                        
				}                                              
			}                              
		}              
	}              
	
	private List<TrackingData> convertTNTTrackingToTrackingData(List<TNTTrackingData> tntTrackingDataList){                      
		List<TrackingData> trackingDataList = new ArrayList<TrackingData>();                    
		for (TNTTrackingData tntData: tntTrackingDataList) {                                    
			if (tntData != null) {                                                         
				TrackingData trackingData = new TrackingData();                                                 
				//trackingData.setPkgStatus(tntData.getSummary_Status_Description_1());                                                      
				String statusCode = tntData.getStatus_Code_1();                                                             
				trackingData.setPkgStatus(statusCode);                                                               
				//trackingData.setEventCity(tntData.getReceiver_City());                                                           
				//trackingData.setEventZipCode(tntData.getReceiver_Postcode());                                                       
				//trackingData.setEventState(tntData.getReceiver_Province());                                                               
				//trackingData.setEventCountry(tntData.getReceiver_Country());                                                           
				trackingData.setEventDate(tntData.getGMT_Event_Date());                                                       
				trackingData.setEventTime(tntData.getGMT_Event_Time());                                                     
				trackingData.setEventCode(tntData.getStatus_Code_1());                                                           
				trackingData.setTrackingNumber(tntData.getCountry_Consignment_Number());                                                            
				trackingDataList.add(trackingData);                                        
			}                              
		}                              
		return trackingDataList;                
	}              
	
	public String processData(List<TrackingData> records) throws Exception{                            
		LogTransaction calT = log.openCAL("TNTFileTrackingProcessor","processData");                                
		Map<TrackingCarrier, List<TrackingEvent>> map = (Map<TrackingCarrier, List<TrackingEvent>>) new HashMap();                                
		try {                                        
			if (records != null && !records.isEmpty()) {                                                          
				if (records.get(0) instanceof TrackingData) {                                                                           
					System.out.println("TrackingData");                                                                      
					List<TrackingData> list = records;                                                                              
					// Iterate Through the records                                                                         
					for (TrackingData data : list) {                                                                                     
						// Create TrackingEvent object                                                                                       
						TrackingEvent trkEvent = new TrackingEvent();                                                                                 
						if (data.getPkgStatus() != null) {                                                                                                                
						TrackingStatusEnum statusEnum = TNTShipmentTrackingProvider.getInstance().mapTrackingStatus(String.valueOf(data.getPkgStatus()));                                                                                                               
						if(statusEnum != null) {                                                                                                                 
							trkEvent.setStatus(statusEnum);                                                                                                              
						} else {                                                                                                                  
							trkEvent.setStatus(TrackingStatusEnum.INVALID);                                                                                                          
						}                                                                                                                              
					}                                                                                              
					// Set Location/Address details for event                                                                                            
					  Address address = null;                                                                                       
					  if (data.getEventCity() != null || data.getEventZipCode() != null ||                                                                                                                         
					  data.getEventState() != null || data.getEventCountry() != null) {                                                                                                                
					  address = new Address();                                                                                            
					  }                                                                                              
					  if (data.getEventCity() != null) {                                                                                                                
					  address.setCity(data.getEventCity().trim());                                                                                      
					  }                                                                                              
					  if (data.getEventZipCode() != null) {                                                                                                       
					  address.setPostalCode(data.getEventZipCode().trim());                                                                                              
					  }                                                                                              
					  if (data.getEventState() != null) {                                                                                                             
					  address.setStateOrProvince(data.getEventState().trim());                                                                                                
					  }                                                                                              
					  Country country = getCountry(data.getEventCountry());                                                                                                
					  if(country!=null){                                                                                                            
					  address.setCountry(country);                                                                                    
					  }                                                                                                
					  trkEvent.setAddress(address);                                                                                  
					  // trkEvent.setLocation(data.getEventCity());                                                                                         
					  if(data.getEventDesc()!=null){                                                                                                  
					  trkEvent.setDescription(data.getEventDesc().trim());                                                                                    
					  }                                                                                              
					  // trkEvent.setTime(data.getEstDeliveryTime());                                                                                                
					  Calendar cal = null;                                                                                         
					  if (data.getEventDate() != null && !data.getEventDate().equals("")) {                                                                                                          
					  cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));                                                                                                              
					  String dateTime = data.getEventDate();                                                                                                             
					  SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMdd");                                                                                                  
					  if (data.getEventTime() != null && !data.getEventTime().equals("")) {                                                                                                                  
					  dateTime = data.getEventDate().trim() + " " + data.getEventTime().trim() + " GMT";                                                                                                                   
					   sdf = new SimpleDateFormat("yyyyMMdd HHmm Z");                                                                                                                        
					   sdf.setTimeZone(TimeZone.getTimeZone("GMT"));                                                                                                       
					   }                                                                                                              
					   try {                                                                                                                        
					   Date date = sdf.parse(dateTime);                                                                                                                     
					   cal.setTime(date);                                                                                                                          
					   trkEvent.setTime(cal);                                                                                                  
					   } catch (ParseException e) {                                                                                                                       
					   calT.markCAL("Incorrect date/Time format: "  + dateTime);                                                                                                      
					   }                                                                                              
					   }                                                                                              
					   if (data.getEventCode() != null) {                                                                                                 
					   trkEvent.setCode(data.getEventCode().trim());                                                                                               
					    }                                                                                             
					     TrackingCarrier trkCarrier = new TrackingCarrier();                                                                                         
						if (data.getTrackingNumber() != null) {                                                                                                  
							trkCarrier.setTrackingNumber(data.getTrackingNumber().trim());                                                                                  
							}                                                                                              
							trkCarrier.setCarrier(currentCarrier);                                                                                     
							// Add trkEvent to map                                                                                 
							 if (map.get(trkCarrier) != null) {                                                                                                                
							  map.get(trkCarrier).add(trkEvent);                                                                                         
							  } else {                                                                                                  
							  List eventList = new LinkedList<TrackingEvent>();                                                                                                            
							  eventList.add(trkEvent);                                                                                                              
							  map.put(trkCarrier, eventList);                                                                                 
							  }                                                                              
							  }                                                                              
							  List<TrackingEventFeed> eventFeedList = new LinkedList<TrackingEventFeed>();                                                                            
							  // Iterate through the map                                                                          
							    for (Map.Entry<TrackingCarrier, List<TrackingEvent>> entry : map.entrySet()) {                                                                                     
							    // Create Tracking Feed Object                                                                                 
							    TrackingEventFeed eventFeed = new TrackingEventFeed();                                                                                                
							    ShippingCarrier shippingCarrier = ShippingCarrier.getInstance(                                                                                                                                                     
							    entry.getKey().getCarrier().trim(),  true);                                                                                    
							    if (shippingCarrier != null) {                                                                                                        
							    eventFeed.setCarrier(shippingCarrier);
							    eventFeed.setTrackingNumber(entry.getKey().getTrackingNumber().trim());                                                                                                  
							    eventFeed.getEvents().addAll(entry.getValue());                                                                                                           
							    // Add the Object to TrackingFeedList                                                                                                          
							    eventFeedList.add(eventFeed);                                                                                                               
							    System.out.println(entry.getKey().getTrackingNumber()+ "/"+ entry.getValue().size() );                                                                                          
							    }                                                                              }                                                                              
							    // Call Orion Service To Update the DB                                                                            
							    calT.markCAL("Calling Orion Service to Update Tracking Events");                                                                                
							    TrackingEventsFeedService feedService = TrackingEventsFeedServiceFactory.getService();                                                                    
							    response = feedService.update(eventFeedList);                                                     
							    }  
							    / *else if (records.get(0) instanceof TrackingMeta) {                                                    }* /  

								System.out.println("process end.");                                       
								}                              
								} catch (Exception e) {                                    
								calT.markCAL("Exception occured in ProcessData " + e);                                               
					  throw new RuntimeException(e);                           
					} finally {                                              
						calT.closeCAL();                                
					}                                
					// iterate through the map and create a list of TrackingEventFeed                           
				return "done";  
			}                
			
			private Country getCountry(String countryCode) {                           LogTransaction calT = log.openCAL("CustomTrackingProcessor","processFile");                            try{                                         if(countryCode!=null && countryCode.length()==2){                                                         Country country = Country.getInstanceAlpha2(countryCode);                                                    return country;                                 }else if(countryCode!=null && countryCode.length()==3){                                                          Country country = Country.getInstance(countryCode,true);                                                        return country;                                               }else{                                                    calT.markCAL("Country code is invalid : "+countryCode);                                                }                              }catch(IllegalArgumentException ie){                                    calT.markCAL("Incorrect ISO3 Country Code : "+countryCode+":"+ie.getMessage());                         }                              return null;         }              class TrackingCarrier {                              String trackingNumber;                                String carrier;                    public String getTrackingNumber() {                                 return trackingNumber;                               }                              public void setTrackingNumber(String trackingNumber) {                                   this.trackingNumber = trackingNumber;                               }                                public String getCarrier() {                                           return carrier;                   }                              public void setCarrier(String carrier) {                                           this.carrier = carrier;                       }                              @Override                         public int hashCode() {                                 String combinedString = trackingNumber + carrier;                                          return combinedString.hashCode();                       }                              @Override                         public boolean equals(Object other) {                                  if ((other instanceof TrackingCarrier)                                                                     && (this.hashCode() == other.hashCode()) && this != null) {                                                              TrackingCarrier otherObject = (TrackingCarrier) other;                                                                if (this.getCarrier().equals(otherObject.getCarrier())                                                                                     && this.getTrackingNumber().equals(                                                                                                                   otherObject.getTrackingNumber())) {                                                                    return true;                                                        } else {                                                                     return false;                                                       }                                              } else {                                                  return false;                                       }                              }              }}
					}
				}
			}
		}
	}
}
*/

