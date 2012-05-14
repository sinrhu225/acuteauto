-- ----------------------------
-- Records for "PRIVILEGE"
-- ----------------------------
INSERT INTO PRIVILEGE VALUES ('31', 'Inquire Orders', 'I_ORDER', 'Can Search for Order Requests');
INSERT INTO PRIVILEGE VALUES ('30', 'Update Orders', 'U_ORDER', 'Can Update Order Requests');
INSERT INTO PRIVILEGE VALUES ('29', 'Read Orders', 'R_ORDER', 'Can only View Order Requests');
INSERT INTO PRIVILEGE VALUES ('28', 'Create Orders', 'C_ORDER', 'Can Create/Edit Order Requests');
INSERT INTO PRIVILEGE VALUES ('27', 'Inquire Advertisements', 'I_ADVT', 'Can search for Advertisements');
INSERT INTO PRIVILEGE VALUES ('26', 'Update Advertisements', 'U_ADVT', 'Can Update Advertisements');
INSERT INTO PRIVILEGE VALUES ('25', 'Read Advertisements', 'R_ADVT', 'Can only Read Advertisement Information');
INSERT INTO PRIVILEGE VALUES ('24', 'Create Advertisements', 'C_ADVT', 'Can Create/Edit Advertisements');
INSERT INTO PRIVILEGE VALUES ('23', 'Inquire Albums', 'I_ALBUM', 'Can Search for Albums');
INSERT INTO PRIVILEGE VALUES ('22', 'Read Albums', 'R_ALBUM', 'Can only View Albums');
INSERT INTO PRIVILEGE VALUES ('21', 'Create Albums', 'C_ALBUM', 'Can Create/Edit Albums');
INSERT INTO PRIVILEGE VALUES ('20', 'Inquire Role', 'I_ROLE', 'Can search for Roles');
INSERT INTO PRIVILEGE VALUES ('19', 'Read Role', 'R_ROLE', 'Can only Read Role Information');
INSERT INTO PRIVILEGE VALUES ('18', 'Create Role', 'C_ROLE', 'Can Create/Edit Role Information');
INSERT INTO PRIVILEGE VALUES ('17', 'Inquire Features', 'I_FEATURE', 'Can Search for Features & Feature Groups');
INSERT INTO PRIVILEGE VALUES ('16', 'Read Features', 'R_FEATURE', 'Can only Read Features & Feature Groups');
INSERT INTO PRIVILEGE VALUES ('15', 'Create Features', 'C_FEATURE', 'Can Create/Edit Features & Feature Groups');
INSERT INTO PRIVILEGE VALUES ('14', 'Inquire Categories', 'I_CATEGORY', 'Can Search for Categories');
INSERT INTO PRIVILEGE VALUES ('13', 'Read Categories', 'R_CATEGORY', 'Can Read Category Information');
INSERT INTO PRIVILEGE VALUES ('12', 'Create Categories', 'C_CATEGORY', 'Can Create/Edit Category Information');
INSERT INTO PRIVILEGE VALUES ('11', 'Inquire Items', 'I_ITEM', 'Can Search for Items');
INSERT INTO PRIVILEGE VALUES ('10', 'Read Items', 'R_ITEM', 'Can only Read Item Information');
INSERT INTO PRIVILEGE VALUES ('9', 'Create Items', 'C_ITEM', 'Can Create/Edit Item Information');
INSERT INTO PRIVILEGE VALUES ('8', 'Inquire Clients', 'I_CLIENT', 'Can Search for Clients');
INSERT INTO PRIVILEGE VALUES ('7', 'Update Clients', 'U_CLIENT', 'Can Edit Client Information');
INSERT INTO PRIVILEGE VALUES ('6', 'Read Clients', 'R_CLIENT', 'Can Read Client Information');
INSERT INTO PRIVILEGE VALUES ('5', 'Create Clients', 'C_CLIENT', 'Can Create/Edit Client Information');
INSERT INTO PRIVILEGE VALUES ('4', 'Inquire Users', 'I_USER', 'Can Search for Users');
INSERT INTO PRIVILEGE VALUES ('3', 'Update Users', 'U_USER', 'Can Edit User Information');
INSERT INTO PRIVILEGE VALUES ('2', 'Read Users', 'R_USER', 'Can only Read User Information');
INSERT INTO PRIVILEGE VALUES ('1', 'Create Users', 'C_USER', 'Can Create/Edit User Information');

-- ----------------------------
-- Records for "RL_ROLE_PRIVILEGE"
-- ----------------------------
INSERT INTO RL_ROLE_PRIVILEGE VALUES ('1', '1');
INSERT INTO RL_ROLE_PRIVILEGE VALUES ('1', '2');
INSERT INTO RL_ROLE_PRIVILEGE VALUES ('1', '3');
INSERT INTO RL_ROLE_PRIVILEGE VALUES ('1', '4');
INSERT INTO RL_ROLE_PRIVILEGE VALUES ('1', '5');
INSERT INTO RL_ROLE_PRIVILEGE VALUES ('1', '6');
INSERT INTO RL_ROLE_PRIVILEGE VALUES ('1', '7');
INSERT INTO RL_ROLE_PRIVILEGE VALUES ('1', '8');
INSERT INTO RL_ROLE_PRIVILEGE VALUES ('1', '9');
INSERT INTO RL_ROLE_PRIVILEGE VALUES ('1', '10');
INSERT INTO RL_ROLE_PRIVILEGE VALUES ('1', '11');
INSERT INTO RL_ROLE_PRIVILEGE VALUES ('1', '12');
INSERT INTO RL_ROLE_PRIVILEGE VALUES ('1', '13');
INSERT INTO RL_ROLE_PRIVILEGE VALUES ('1', '14');
INSERT INTO RL_ROLE_PRIVILEGE VALUES ('1', '15');
INSERT INTO RL_ROLE_PRIVILEGE VALUES ('1', '16');
INSERT INTO RL_ROLE_PRIVILEGE VALUES ('1', '17');
INSERT INTO RL_ROLE_PRIVILEGE VALUES ('1', '18');
INSERT INTO RL_ROLE_PRIVILEGE VALUES ('1', '19');
INSERT INTO RL_ROLE_PRIVILEGE VALUES ('1', '20');
INSERT INTO RL_ROLE_PRIVILEGE VALUES ('1', '21');
INSERT INTO RL_ROLE_PRIVILEGE VALUES ('1', '22');
INSERT INTO RL_ROLE_PRIVILEGE VALUES ('1', '23');
INSERT INTO RL_ROLE_PRIVILEGE VALUES ('1', '24');
INSERT INTO RL_ROLE_PRIVILEGE VALUES ('1', '25');
INSERT INTO RL_ROLE_PRIVILEGE VALUES ('1', '26');
INSERT INTO RL_ROLE_PRIVILEGE VALUES ('1', '27');
INSERT INTO RL_ROLE_PRIVILEGE VALUES ('1', '28');
INSERT INTO RL_ROLE_PRIVILEGE VALUES ('1', '29');
INSERT INTO RL_ROLE_PRIVILEGE VALUES ('1', '30');
INSERT INTO RL_ROLE_PRIVILEGE VALUES ('1', '31');



-- ----------------------------
-- Records for "ROLE"
-- ----------------------------
INSERT INTO ROLE VALUES ('1', 'Application Administrator', 'Can edit all');
INSERT INTO ROLE VALUES ('2', 'Store Administrator', 'Can edit everything in his store');
INSERT INTO ROLE VALUES ('3', 'Employee', 'Employee who attends to the customer');
INSERT INTO ROLE VALUES ('4', 'Customer', 'Customer who registers into our system');


-- ----------------------------
-- Records for "ENUM_TYPE"
-- ----------------------------
INSERT INTO ENUM_TYPE VALUES ('1', 'USER_TYPE', 'Type of User', null);
INSERT INTO ENUM_TYPE VALUES ('2', 'USER_STATUS', 'Status of User', null);
INSERT INTO ENUM_TYPE VALUES ('3', 'LOCATION_TYPE', 'Type of Location', null);
INSERT INTO ENUM_TYPE VALUES ('4', 'STYLE_TYPE', 'Style of a Car : Trim/Package', null);
INSERT INTO ENUM_TYPE VALUES ('5', 'BODY_TYPE', 'Body type of a Vehicle', null);
INSERT INTO ENUM_TYPE VALUES ('6', 'CATEGORY_TYPE', 'Type of User', null);
INSERT INTO ENUM_TYPE VALUES ('7', 'FUEL_TYPE', 'Type of Fuel used for the car', null);
INSERT INTO ENUM_TYPE VALUES ('8', 'WARRANTY_TYPE', 'Type of Warranty', null);
INSERT INTO ENUM_TYPE VALUES ('9', 'VEHICLE_STATUS', 'Status of a car', null);
INSERT INTO ENUM_TYPE VALUES ('10', 'VEHICLE_CONDITION', 'Condition of a car', null);
INSERT INTO ENUM_TYPE VALUES ('11', 'DISPLAY_TYPE', 'Type of Display', null);
INSERT INTO ENUM_TYPE VALUES ('12', 'IMAGE_TYPE', 'Type of Photo for the car', null);
INSERT INTO ENUM_TYPE VALUES ('13', 'AD_STATUS', 'Status of Advertisement', null);
INSERT INTO ENUM_TYPE VALUES ('14', 'AD_UNITS', 'Unit of Advertisement', null);
INSERT INTO ENUM_TYPE VALUES ('15', 'LOAN_STATUS', 'Status of Loan', null);
INSERT INTO ENUM_TYPE VALUES ('16', 'FINANCE_TYPE', 'Type of Finance', null);
INSERT INTO ENUM_TYPE VALUES ('17', 'ACCOUNT_TYPE', 'Type of Account', null);
INSERT INTO ENUM_TYPE VALUES ('18', 'INQUIRY_TYPE', 'Type of Inquiry', null);
INSERT INTO ENUM_TYPE VALUES ('19', 'TRANSMISSION_TYPE', 'Type of Transmission', null);
INSERT INTO ENUM_TYPE VALUES ('20', 'DRIVE_TRAIN_TYPE', 'Type of Drive Train', null);
INSERT INTO ENUM_TYPE VALUES ('21', 'RESIDENCE_TYPE', 'Type of Residence', null);

-- ----------------------------
-- Records for "ENUM"
-- ----------------------------
-- USER_TYPE
INSERT INTO ENUM VALUES ('1', '1', 'Client', 'Client', null);
INSERT INTO ENUM VALUES ('2', '1', 'Customer', 'Customer', null);
-- USER_STATUS
INSERT INTO ENUM VALUES ('10', '2', 'Active', 'Active', null);
INSERT INTO ENUM VALUES ('11', '2', 'In Active', 'In Active', null);
INSERT INTO ENUM VALUES ('12', '2', 'Suspended', 'Suspended', null);
-- LOCATION_TYPE
INSERT INTO ENUM VALUES ('20', '3', 'Primary', 'Primary', null);
INSERT INTO ENUM VALUES ('21', '3', 'Secondary', 'Secondary', null);
-- STYLE_TYPE
INSERT INTO ENUM VALUES ('30', '4', 'Trim', 'Trim', null);
INSERT INTO ENUM VALUES ('31', '4', 'Package', 'Package', null);
-- BODY_TYPE
INSERT INTO ENUM VALUES ('40', '5', 'Sedan', 'Sedan', null);
INSERT INTO ENUM VALUES ('41', '5', 'Wagon', 'Wagon', null);
INSERT INTO ENUM VALUES ('42', '5', 'Crossover', 'Crossover', null);
INSERT INTO ENUM VALUES ('43', '5', 'Luxury', 'Luxury', null);
INSERT INTO ENUM VALUES ('44', '5', 'Coupe', 'Coupe', null);
INSERT INTO ENUM VALUES ('45', '5', 'Hatchback', 'Hatchback', null);
INSERT INTO ENUM VALUES ('46', '5', 'SUV', 'SUV', null);
INSERT INTO ENUM VALUES ('47', '5', 'Hybrid', 'Hybrid', null);
INSERT INTO ENUM VALUES ('48', '5', 'Convertible', 'Convertible', null);
INSERT INTO ENUM VALUES ('49', '5', 'Pickup', 'Pickup', null);
INSERT INTO ENUM VALUES ('50', '5', 'Minivan', 'Minivan', null);
INSERT INTO ENUM VALUES ('51', '5', 'Truck', 'Truck', null);
-- CATEGORY_TYPE
INSERT INTO ENUM VALUES ('60', '6', 'Home Page', 'Home Page', 'Categories displayed on Home Page');
INSERT INTO ENUM VALUES ('61', '6', 'Main Menu', 'Main Menu', 'Main Menu displayed as part of website header');
-- FUEL_TYPE
INSERT INTO ENUM VALUES ('70', '7', 'Gasoline', 'Gasoline', null);
INSERT INTO ENUM VALUES ('71', '7', 'Prem Gas', 'Premium Gasoline', null);
INSERT INTO ENUM VALUES ('72', '7', 'Diesel', 'Diesel', null);
-- WARRANTY_TYPE
INSERT INTO ENUM VALUES ('80', '8', 'Standard', 'Standard', null);
INSERT INTO ENUM VALUES ('81', '8', 'Extended', 'Extended', null);
INSERT INTO ENUM VALUES ('82', '8', 'No Warranty', 'As Is - No Warranty', null);
-- VEHICLE_STATUS
INSERT INTO ENUM VALUES ('90', '9', 'Available', 'Available', null);
INSERT INTO ENUM VALUES ('91', '9', 'Sold', 'Sold', null);
INSERT INTO ENUM VALUES ('92', '9', 'On Hold', 'On Hold', null);
-- CONDITION
INSERT INTO ENUM VALUES ('100', '10', 'New', 'New', null);
INSERT INTO ENUM VALUES ('101', '10', 'Used', 'Pre-Owned', null);
INSERT INTO ENUM VALUES ('102', '10', 'Certified', 'Certified', null);
-- DISPLAY_TYPE
INSERT INTO ENUM VALUES ('110', '11', 'Select One', 'Select One', null);
INSERT INTO ENUM VALUES ('111', '11', 'Select Many', 'Select Many', null);
-- IMAGE_TYPE
INSERT INTO ENUM VALUES ('120', '12', 'Primary', 'Primary', null);
-- AD_STATUS
INSERT INTO ENUM VALUES ('130', '13', 'Active', 'Active', null);
INSERT INTO ENUM VALUES ('131', '13', 'Suspended', 'Suspended', null);
INSERT INTO ENUM VALUES ('132', '13', 'Expired', 'Expired', null);
-- AD_UNITS
INSERT INTO ENUM VALUES ('140', '14', 'Hourly', 'Hourly', null);
INSERT INTO ENUM VALUES ('141', '14', 'Daily', 'Daily', null);
INSERT INTO ENUM VALUES ('142', '14', 'Weekly', 'Weekly', null);
INSERT INTO ENUM VALUES ('143', '14', 'Monthly', 'Monthly', null);
INSERT INTO ENUM VALUES ('144', '14', 'Yearly', 'Yearly', null);
-- LOAN_STATUS
INSERT INTO ENUM VALUES ('150', '15', 'Submitted', 'Submitted', null);
INSERT INTO ENUM VALUES ('151', '15', 'Under Review', 'Under Review', null);
INSERT INTO ENUM VALUES ('152', '15', 'Approved', 'Approved', null);
-- FINANCE_TYPE
INSERT INTO ENUM VALUES ('160', '16', 'Purchase', 'Purchase', null);
INSERT INTO ENUM VALUES ('161', '16', 'Lease', 'Lease', null);
-- ACCOUNT_TYPE
INSERT INTO ENUM VALUES ('170', '17', 'Checking', 'Checking', null);
INSERT INTO ENUM VALUES ('171', '17', 'Savings', 'Savings', null);
-- INQUIRY_TYPE
INSERT INTO ENUM VALUES ('180', '18', 'Reserved', 'Reserved', 'For future use');
-- TRANSMISSION_TYPE
INSERT INTO ENUM VALUES ('190', '19', 'Automatic', 'Automatic', 'Automatic Transmission');
INSERT INTO ENUM VALUES ('191', '19', 'Manual', '5 Speed Manual', '5 Speed Manual Transmission');
-- DRIVE_TRAIN_TYPE
INSERT INTO ENUM VALUES ('210', '20', 'AWD', 'AWD', 'All Wheel Drive');
INSERT INTO ENUM VALUES ('211', '20', 'FWD', 'FWD', 'Front Wheel Drive');
INSERT INTO ENUM VALUES ('212', '20', 'RWD', 'RWD', 'Rear Wheel Drive');
INSERT INTO ENUM VALUES ('213', '20', 'FIVE WD', '4WD', 'Four Wheel Drive');
-- RESIDENCE_TYPE
INSERT INTO ENUM VALUES ('220', '21', 'OWNED', 'Owned', 'Owned Property');
INSERT INTO ENUM VALUES ('221', '21', 'RENTED', 'Rented', 'Rented Property');

------------------------------------
------RECORDS FOR FEATURE_GROUP-----
------------------------------------
INSERT INTO FEATURE_GROUP VALUES(1, 'OPTIONS', 111, 'Vehicle Options', 0, null);

------------------------------------
------RECORDS FOR FEATURE-----------
------------------------------------
