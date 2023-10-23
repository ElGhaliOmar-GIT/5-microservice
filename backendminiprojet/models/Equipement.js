import mongoose from "mongoose";

const EquipementSchema = new mongoose.Schema({
  brandModel: {
    type: String,
  },
  registrationNumber: {
    type: String,
  },
  capacity: {
    type: Number,
  },
  status: {
    type: String,
    enum: ["En service", "En réparation", "Hors service"],
    default: "En service",
  },
  purchaseDate: {
    type: Date,
  }
}
);

export default mongoose.model("equipement", EquipementSchema);