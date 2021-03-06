.. _board-ui:

Board-UI
--------
The ``Board`` is the landing page that appears in your browswer when you start Karamel. The ``Board`` is a view on a cluster definition file that you load. You can modify the cluster using the UI (adding/removing recipes, entering parameter values, save updated cluster definitions) and run the cluster definition from the UI. This way, inexperienced users can launch clusters without needing to read cluster definitions in YAML.

Load Cluster Definition
````````````````````````````
Click on the menu item, and then click on ``Load Cluster Defn``:

  .. figure:: ../imgs/board1.png
     :alt: Load Cluster Definition.
     :figclass: align-center
     :scale: 50
	   
     Load Cluster Definition.
	   
Lists are shown in the board perspective of the UI, where each list represents a group of machines that install the same application stack. At the top of each list you see the group-name followed by the number of machines in that group (in parentheses). Each list consists of a set of cards, where each card represents a service (Chef recipe) that will be installed on all the machines in that group. Chef recipes are programs written in Ruby that contain instructions for how to install and configure a piece of software or run an experiment.

  .. figure:: ../imgs/board2.png
     :alt: Lists of cards in Karamel. Each card is a Chef recipe.
     :figclass: align-center
     :scale: 80

     Lists of cards in Karamel. Each card is a Chef recipe.	  
	     
Change group name and size
```````````````````````````````
To change the GroupName and/or number of machines in each group, double click on the header of the group. In the following dialog, you can make your changes and submit them (to indicate your are finished).

  .. figure:: ../imgs/board3.png
     :alt: Changing the Number of nodes in a NodeGroup
     :figclass: align-center
     :scale: 80

     Changing the number of nodes in a NodeGroup
	     
.. raw:: latex
    \newpage
	     
Add a new recipe to a group
``````````````````````````````
In the top-left icon in the header of each group, there is a menu to update the group. Select the ``Add recipe`` menu item:
  
  .. figure:: ../imgs/board4.png
     :alt: Adding a Chef recipe to a node group.
     :figclass: align-center
     :scale: 50
	     
     Adding a Chef recipe to a node group.
	      
In order to add a recipe to a group, you must enter the GitHub URL for the (karamelized) Chef cookbook where your recipe resides, and then press ``fetch`` to load available recipes from the cookbook. Choose your recipe from the combo-box below:
  
  .. figure:: ../imgs/board5.png
     :alt: Adding a Chef recipe to a node group.
     :figclass: align-center
     :scale: 80
	     
     Adding a Chef recipe from a GitHub cookbook to a node group.
	 
Customize Chef attributes for a group
````````````````````````````````````````
Parameters (Chef attributes) can be entered  within the scope of a NodeGroup: group scope values have higher precedence than (override) global cluster scope values. To update chef attributes for a group, select its menu item from the group settings menu:
 
  .. figure:: ../imgs/board6.png
     :alt: Updating Chef Attributes at the Group level.
     :figclass: align-center
     :scale: 50

     Updating Chef Attributes at the Group level.
	      
In the dialog below, there is a tab per used cookbook in that group, in each tab you see all customizable attributes, some of them are mandatory and some optional with some default values. Users must set a value for all of the mandatory attributes (or accept the default value, if one is given).
 
  .. figure:: ../imgs/board7.png
     :alt: Entering attribute values to customize service.
     :figclass: align-center
     :scale: 90

     Entering attribute values to customize service.


.. raw:: latex
    \newpage
	 
Customize cloud provider for a group
```````````````````````````````````````
Cluster definition files support the use of multiple (different) cloud providers within the same cluster definition. Each group can specify its own cloud provider. This way, we can support multi-cloud deployments. Like attributes, cloud provider settings at the NodeGroup scope will override cloud provider settings at the global scope. Should you have multi-cloud settings in in your cluster, at launch time you must supply credentials for each cloud separately in the launch dialog.
  
  .. figure:: ../imgs/board8.png
     :alt: Multi-cloud deployments are supported by specifying different cloud providers for different node groups.
     :figclass: align-center
     :scale: 50

     Multi-cloud deployments are supported by specifying different cloud providers for different node groups.	      
	      
Choose the cloud provider for the current group then you will see moe detailed settings for the cloud provider.

  .. figure:: ../imgs/board9.png
     :alt: Configuring a cloud provider per Node Group.
     :figclass: align-center
     :scale: 90

     Configuring a cloud provider per Node Group.
	      
Delete a group
`````````````````
If you want to delete a group find the menu-item in the group menu. 
  
  .. figure:: ../imgs/board10.png
     :alt: Delete a Node Group.
     :figclass: align-center
     :scale: 50

     Delete a Node Group.
	      
Once you delete a group the list and all the settings related to that group will be disappeared forever.  
  
  .. figure:: ../imgs/board11.png
     :alt: Node Group has been deleted.
     :figclass: align-center
     :scale: 50

     Delete Confirmation.
	      
	      
Update cluster-scope attributes
```````````````````````````````````````
When you are done with your group settings you can have some global values for Chef attributes. By choosing Configure button in the middle of the top bar a configuration dialog will pop up, there you see several tabs each named after one used chef-cookbook in the cluster definition. Those attributes are pre-built by cookbook designers for run-time customization. There are two types of attributes mandatory and optional - most of them usually have a default value but if they don't, the user must fill in mandatory values to be able to proceed. 

  .. figure:: ../imgs/board12.png
     :alt: Filling in optional and mandatory attributes.
     :figclass: align-center
     :scale: 50

     To fill in optional and mandatory attributes.
	      
By default each cookbook has a parameter for the operating system's user-name and group-name. It is recommended to set the same user and group for all cookbooks that you don't face with permission issues. 

It is also important to fine-tune your systems with the right parameters, for instance according to type of the machines in your cluster you should allocate enough memory to each system. 

  .. figure:: ../imgs/board13.png
     :alt:
     :figclass: align-center
     :scale: 90

     Filling in optional and mandatory attributes.
		
Start to Launch Cluster
```````````````````````
Finally you have to launch your cluster by pressing launch icon in the top bar. There exist a few tabs that user must go through all of them, you might have to specify values and confirm everything. Even though Karamel caches those values, you have to always confirm that Karamel is allowed to use those values for running your cluster.

  .. figure:: ../imgs/board14.png
     :alt:
     :figclass: align-center
     :scale: 90

     Launch Button.
		      
Set SSH Keys
````````````
In this step first you need to specify your ssh key pair - Karamel uses that to establish a secure connection to virtual machines. For Linux and Mac operating systems, Karamel finds the default ssh key pair in your operating system and will use it.
  
  .. figure:: ../imgs/board15.png
     :alt:
     :figclass: align-center
     :scale: 90
	      
     SSH key paths.

Generate SSH Key
````````````````
If you want to change the default ssh-key you can just check the advance box and from there ask Karamel to generate a new key pair for you. 

Password Protected SSH Keys
```````````````````````````
If your ssh key is password-protected you need to enter your password in the provided box, and also in case you use bare-metal (karamel doesn't fork machines from cloud) you have to give sudo-account access to your machines. 

  .. figure:: ../imgs/board16.png
     :alt:
     :figclass: align-center
     :scale: 90

     Advanced options for SSH keys.
	      
Cloud Provider Credentials
``````````````````````````
In the second step of launch you need to give credentials for accessing the cloud of your choice. If your cluster is running on a single cloud a tab related to that cloud will appear in the launch dialog and if you use multi-cloud a separate tab for each cloud will appear. Credentials are usually in different formats for each cloud, for more detail information please find it in the related cloud section. 

  .. figure:: ../imgs/board17.png
     :alt:
     :figclass: align-center
     :scale: 80
     
     Provider-specific credentials.

Final Control
`````````````
When you have all the steps passed in the summary tab you can launch your cluster, it will bring you to the :ref:`terminal <karamel-terminal>` there you can control the installation of your cluster.

  .. figure:: ../imgs/board18.png
     :alt:
     :figclass: align-center
     :scale: 80

     Validity summary for keys and credentials. 

