import express from "express";

import {
    createactivityTransport,
    updateactivityTransport,
    deleteactivityTransport,
    getactivityTransport,
    getactivityTransports,

} from "../controllers/ActivityTransport.js"

const router = express.Router();
//CREATE
router.post("/",  createactivityTransport);

//UPDATE
router.put("/:id", updateactivityTransport);
//DELETE
router.delete("/:id", deleteactivityTransport);
//GET

router.get("/:id", getactivityTransport);
//GET ALL

router.get("/", getactivityTransports);

export default router;


//cus_MxkG2fgPmlPQ1d