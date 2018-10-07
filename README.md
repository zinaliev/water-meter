# Water Meter

The app calculates amount of water stored on a landscape after a rain is over   
The landscape is described as a sequence of hills' heights and represented as an int array  

## Example
##### Landscape (hill heights) array
5 2 3 4 5 4 0 3 1

##### The landscape before a rain starts

    #           #
    #        #  #  #
    #     #  #  #  #     #
    #  #  #  #  #  #     #
    #  #  #  #  #  #     #  #
    
    5  2  3  4  5  4  0  3  1
    
**#** symbol represents a hill cell

##### The landscape after a rain is over  

    #  ~  ~  ~  #
    #  ~  ~  #  #  #
    #  ~  #  #  #  #  ~  #
    #  #  #  #  #  #  ~  #
    #  #  #  #  #  #  ~  #  #
    
    5  2  3  4  5  4  0  3  1
    
**~** symbol represents a water cell 
  
The answer for the provided landscape should be **9** 

## Build
Build the app and run all tests  
 
    mvn clean package

## Run manual input mode
Run application without command line arguments and follow the instructions

    Press [CONTROL + C] to terminate
    ---
    Type space separated hills' heights to describe a landscape and press [ENTER]
    5 2 3 4 5 4 0 3 1
    
    # ~ ~ ~ #         
    # ~ ~ # # #       
    # ~ # # # # ~ #   
    # # # # # # ~ #   
    # # # # # # ~ # # 
    
    Water: 9

## Run landscape auto-generation mode
Run application with `auto` command line argument and enjoy mountains' views
    
    Press [CONTROL + C] to terminate
    ---
    Press [ENTER] to generate a new landscape
    
    20 hill(s) landscape generated:
    3, 3, 4, 1, 2, 1, 5, 7, 5, 5, 0, 5, 8, 3, 3, 2, 0, 5, 3, 7
    
                            #               
                  # ~ ~ ~ ~ # ~ ~ ~ ~ ~ ~ # 
                  # ~ ~ ~ ~ # ~ ~ ~ ~ ~ ~ # 
                # # # # ~ # # ~ ~ ~ ~ # ~ # 
        # ~ ~ ~ # # # # ~ # # ~ ~ ~ ~ # ~ # 
    # # # ~ ~ ~ # # # # ~ # # # # ~ ~ # # # 
    # # # ~ # ~ # # # # ~ # # # # # ~ # # # 
    # # # # # # # # # # ~ # # # # # ~ # # # 
    
    Water: 47