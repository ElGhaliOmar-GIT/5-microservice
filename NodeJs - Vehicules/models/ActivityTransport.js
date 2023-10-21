import mongoose from "mongoose";
const ActivitytransportSchema = new mongoose.Schema(
  {

    startTime :{
        type : Date ,
    }
     ,
     endTime : {
        type : Date

    } , 
    description : { 
        type : String

    },

    driver : {
        type : String
    } , 

    status : {
        type : String 
    } 

  },

);

export default mongoose.model("activityTransport", ActivitytransportSchema);
