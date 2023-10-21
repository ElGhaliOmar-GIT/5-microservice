import express from "express";
import dotenv from "dotenv";
import mongoose from "mongoose";
import ActivityTransportRoute from "./routes/ActivityTransport.js"
import EquipementRoute from "./routes/Equipement.js"
import cookieParser from "cookie-parser";
import dayjs from "dayjs"
import cors from "cors";
import ISODate from "isodate" ; 


const app = express();
dotenv.config();
export const day= dayjs(new Date ).format('YYYY-MM-DDThh:mm:ss.00Z') ;
export const connect = async () => {
  try {
    await mongoose.connect(process.env.MONGO);
    console.log("Connected to mongoDB.");
  } catch (error) {
    throw error;
  }
};

mongoose.connection.on("disconnected", () => {
  console.log("mongoDB disconnected!");
});







//middlewares
app.use(cors())
app.use(cookieParser())
app.use(express.json());




app.use("/api/activityTransport",ActivityTransportRoute );
app.use("/api/equipement",EquipementRoute );


app.use((err, req, res, next) => {
  const errorStatus = err.status || 500;
  const errorMessage = err.message || "Something went wrong!";
  return res.status(errorStatus).json({
    success: false,
    status: errorStatus,
    message: errorMessage,
    stack: err.stack,
  });
});

app.listen(8800, () => {
  connect();
  console.log("Connected to backend.",ISODate(new Date ) , new Date().getTime()  );
});
