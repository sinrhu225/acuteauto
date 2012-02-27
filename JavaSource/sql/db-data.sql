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