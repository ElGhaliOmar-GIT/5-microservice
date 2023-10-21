import activityTransport from "../models/ActivityTransport.js";



export const createactivityTransport = async (req, res, next) => {
    const newativictyTransport = new activityTransport(req.body);

    try {
        const savedativictyTransport = await newativictyTransport.save();
        res.status(200).json(savedativictyTransport);
    } catch (err) {
        next(err);
    }
};




export const updateactivityTransport = async (req, res, next) => {
    try {
        const updatedactivityTransport = await activityTransport.findByIdAndUpdate(
            req.params.id,
            { $set: req.body },
            { new: true }
        );
        res.status(200).json(updatedactivityTransport);
    } catch (err) {
        next(err);
    }
};


export const deleteactivityTransport = async (req, res, next) => {
    try {
        await activityTransport.findByIdAndDelete(req.params.id);
        res.status(200).json("Activity Transport has been deleted.");
    } catch (err) {
        next(err);
    }
};

export const getactivityTransport = async (req, res, next) => {
    try {
        const activityTransports = await activityTransport.findById(req.params.id);
        res.status(200).json(activityTransports);
    } catch (err) {
        next(err);
    }
};
export const getactivityTransports = async (req, res, next) => {
    try {
        const activityTransports = await activityTransport.find();
        
        
        res.status(200).json(activityTransports);
    } catch (err) {
        next(err);
    }
};