import equipement from "../models/Equipement.js";



export const createequipement = async (req, res, next) => {
    const newequipement = new equipement(req.body);

    try {
        const savedequipement = await newequipement.save();
        res.status(200).json(savedequipement);
    } catch (err) {
        next(err);
    }
};




export const updateequipement = async (req, res, next) => {
    try {
        const updatedequipement = await equipement.findByIdAndUpdate(
            req.params.id,
            { $set: req.body },
            { new: true }
        );
        res.status(200).json(updatedequipement);
    } catch (err) {
        next(err);
    }
};


export const deleteequipement = async (req, res, next) => {
    try {
        await equipement.findByIdAndDelete(req.params.id);
        res.status(200).json("Equipement has been deleted.");
    } catch (err) {
        next(err);
    }
};

export const getequipement = async (req, res, next) => {
    try {
        const equipements = await equipement.findById(req.params.id);
        res.status(200).json(equipements);
    } catch (err) {
        next(err);
    }
};
export const getequipements = async (req, res, next) => {
    try {
        const equipements = await equipement.find();
        
        
        res.status(200).json(equipements);
    } catch (err) {
        next(err);
    }
};