#Program computes the average for each column of numbers in a file with the following format:
# 1 2 3
# 5 4 6
# 0 2 4
# The data file may have any number of rows, but will always have 3 columns.

#Define the function three_column_avg()
def three_column_avg():
    #Create column lists 
    column_1_list = []
    column_2_list = []
    column_3_list = []

    #Ask the user for the file they wish to use 
    with open(input("Please enter the file wyou wish to take the averages of for each column: ")) as f:
        #Set variable 'line' to readlines in the file 
        line = f.readlines()
        #Create a for loop that goes through each line and splits the numbers into their respective column lists
        for x in line:
            column_1_list.append(float(x.split(' ')[0]))
            column_2_list.append(float(x.split(' ')[1]))
            column_3_list.append(float(x.split(' ')[2]))
    #Take the sum of the numbers in each of the column lists 
    column_1_sum = sum(column_1_list)
    column_2_sum = sum(column_2_list)
    column_3_sum = sum(column_3_list)
    #Calculate the average based on the length of each list
    column_1_avg = column_1_sum/len(column_1_list)
    column_2_avg = column_2_sum/len(column_2_list)
    column_3_avg = column_3_sum/len(column_3_list)
    #Print the results to the console up to two decimal places
    print("The average of column 1 is {:.02f}.".format(column_1_avg))
    print("The average of column 2 is {:.02f}.".format(column_2_avg))
    print("The average of column 3 is {:.02f}.".format(column_3_avg))
#Call the function three_column_avg() into use 
three_column_avg()

