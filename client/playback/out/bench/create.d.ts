import { schema } from 'battlecode-schema';
import { flatbuffers } from 'flatbuffers';
export declare function createHeader(builder: flatbuffers.Builder): flatbuffers.Offset;
export declare function createVecTable(builder: flatbuffers.Builder, xs: number[], ys: number[]): number;
export declare function createEventWrapper(builder: flatbuffers.Builder, event: flatbuffers.Offset, type: schema.Event): flatbuffers.Offset;
export declare function createBenchGame(aliveCount: number, churnCount: number, moveCount: number, turns: number): any;
export declare function createWanderGame(unitCount: number, turns: number): any;
