# MiniOS
  This is a siumlated operating system. The resources include Printers, Writers/Readers, Disks, Managers, and the Users. The program will use threads to represent each running resource. Users will be represented by a list of instructions. The program runs in parallel with hazard handling so that processes do not overwrite one another.
## Roles
#### Printers: Role to only print out when the users requests.
#### Writers/Readers: Role to only writer and read.
#### Disks: Stores and return read values.
#### Managers: Keeps track of the threads that are accessing certain resource.
#### Users: Gives commands which command printers and W/R 
