package com;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MDA {
    
    // Define a Request class to hold request information
    class Request {
        int id;
        ArrayList<Module> modules;
        ArrayList<Device> feasibleDevices;
        public Request(int id, ArrayList<Module> modules, ArrayList<Device> feasibleDevices) {
            this.id = id;
            this.modules = modules;
            this.feasibleDevices = feasibleDevices;
        }
    }
    
    // Define a Module class to hold module information
    class Module {
        int id;
        int size;
        public Module(int id, int size) {
            this.id = id;
            this.size = size;
        }
    }
    
    // Define a Device class to hold device information
    class Device {
        int id;
        int capacity;
        int numDeployed;
        public Device(int id, int capacity, int numDeployed) {
            this.id = id;
            this.capacity = capacity;
            this.numDeployed = numDeployed;
        }
        public boolean canDeploy(Module module) {
            return (numDeployed + 1 <= capacity);
        }
    }
    
    // Define a Deployment class to hold deployment information
    class Deployment {
        int requestId;
        int moduleId;
        int deviceId;
        public Deployment(int requestId, int moduleId, int deviceId) {
            this.requestId = requestId;
            this.moduleId = moduleId;
            this.deviceId = deviceId;
        }
    }
    
    // Define a helper function to sort requests by total module size
    private void sortRequests(ArrayList<Request> requests) {
        Collections.sort(requests, new Comparator<Request>() {
            public int compare(Request r1, Request r2) {
                int sum1 = r1.modules.stream().mapToInt(m -> m.size).sum();
                int sum2 = r2.modules.stream().mapToInt(m -> m.size).sum();
                if (sum1 != sum2) {
                    return sum1 - sum2;
                } else {
                    return r1.modules.size() - r2.modules.size();
                }
            }
        });
    }
    
    // Define the MDA algorithm
    public ArrayList<Deployment> moduleDeployment(ArrayList<Request> requests, ArrayList<Device> devices) {
        // Sort requests by total module size in ascending order
        sortRequests(requests);
        
        // Sort devices by capacity in ascending order
        Collections.sort(devices, new Comparator<Device>() {
            public int compare(Device d1, Device d2) {
                return d1.capacity - d2.capacity;
            }
        });
        
        // Initialize deployment plan
        ArrayList<Deployment> plan = new ArrayList<>();
        
        // Iterate over each request and its modules
        for (Request request : requests) {
            for (Module module : request.modules) {
                // Sort feasible devices by number of deployed modules in ascending order
                ArrayList<Device> feasibleDevices = request.feasibleDevices;
                Collections.sort(feasibleDevices, new Comparator<Device>() {
                    public int compare(Device d1, Device d2) {
                        return d1.numDeployed - d2.numDeployed;
                    }
                });
                
                // Find the first feasible device that can deploy the module
                boolean deployed = false;
                for (Device device : feasibleDevices) {
                    if (device.canDeploy(module)) {
                        plan.add(new Deployment(request.id, module.id, device.id));
                        device.numDeployed++;
                        deployed = true;
                        break;
                    }
                }
                
                // If no feasible device can deploy the module
            }
        }
        return plan;
    }
}


