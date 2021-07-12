##DEDEN FARHAN || 0618104038
##REZA SETYADI || 0618104057

# R2DBC Postgresql Reactive
Following are the steps to make archetype for Maven : 
1. <code>mvn clean install</code>
2. <code>mvn archetype:create-from-project</code>
3. <code>cd target/generated-sources/archetype</code>
4. <code>mvn install</code>
5. Done!!


# Final Project BackEnd Details

#Layout Structure: Rest Web -> Service Api -> Service Impl -> Data Access Object(DAO) -> Entity -> Libraries<br/>
  ``Rest Web is where the endpoints are.``<br/>
  ``Service contains the logical functionalities.``<br/>
  ``DAO consist of the repository interfaces that will be created in the database; Each repository interface represents a table.``<br/>
  ``Rest Web Model are used to be the models for requests, responses endpoint``<br/>
  ``collections model are in Entity<br/>``
  <br/>


## BrokerController: Manages the CRUD and applications related to broker property<br/>
  1. addBroker - adds a broker to the database<br/>
  2. updateBroker - update`s broker to the database<br/>
  3. findById - get a broker record from the database<br/>
  4. deleteBroker - delete broker from the database<br/>
  5. findAll - get all broker with paginated in the database<br/>
  <br/>

## HouseController: Manages the CRUD and applications related to house property<br/>
1. addHouse - adds a house to the database<br/>
2. updateHouse - update`s house to the database<br/>
3. findById - get a house record from the database<br/>
4. deleteHouse - delete house from the database<br/>
5. findAll - get all house with paginated in the database<br/>
   <br/>

## BookingController: Booking related to booked property<br/>
1. createBooking - booking house by customer<br/>
2. findAll - get all data booking with paginated in the database<br/>
   <br/>