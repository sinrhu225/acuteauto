Steps to add a new Client.

# Prerequisites #

  * Client's First & Last Name
  * Client's Email Address
  * Client's Phone Number
  * Client's Physical Address
  * Client's current/preferred domain
  * Which package the Client is interested in


# Detailed Steps #

  * Transfer the traffic to acuteauto.com from the client's domain.
  * Register an Addon Domain on acuteauto.com
  * Create a Client on Billing - welcome2@cuteauto
  * Create an Order for this client.
  * Activate the Order.

# Acute Auto Billing Record Creation #
  * Make sure to populate the aid column in the Client with the new domain name.

# Acute Auto Client Record Creation #

  * Run the new client initialization batch process to load the new client data in the acute client database. Make sure that the Alien Id and the Domain Name are passed to the batch process.
  * Alien Id is the Fk reference to the Client Id in the billing system.

# Acute Auto Static Content #

  * Create/Copy new set of images under the new Client Id belonging to this client.


# Acute Auto Server Configuration #

  * Update the server.xml under /home/acuteprod/jvm/servers/tomcat\_prod.