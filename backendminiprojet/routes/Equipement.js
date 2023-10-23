import express from "express";

import {
    createequipement,
    updateequipement,
    deleteequipement,
    getequipement,
    getequipements,

} from "../controllers/Equipement.js"

const router = express.Router();
//CREATE
router.post("/",  createequipement);

//UPDATE
router.put("/:id", updateequipement);
//DELETE
router.delete("/:id", deleteequipement);
//GET

router.get("/:id", getequipement);
//GET ALL

router.get("/", getequipements);

export default router;


//cus_MxkG2fgPmlPQ1d